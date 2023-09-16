package com.wolfnode.wolf2w.auth.filter;

import com.wolfnode.wolf2w.auth.anno.RequireLogin;
import com.wolfnode.wolf2w.core.exception.BussinessException;
import com.wolfnode.wolf2w.core.utils.JwtUtils;
import com.wolfnode.wolf2w.redis.utils.RedisCache;
import com.wolfnode.wolf2w.user.vo.LoginUser;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.coyote.http11.upgrade.UpgradeServletInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.lang.invoke.MethodHandle;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author zhouyihang
 * @version 1.0
 * @description: TODO
 * @date 2023/9/16 17:07
 */

public class LoginInterceptor implements HandlerInterceptor {

    public LoginInterceptor(RedisCache redisCache) {
        this.redisCache = redisCache;
    }

    private RedisCache redisCache;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(!(handler instanceof HandlerMethod handlerMethod)){
            //静态资源或者预处理
            return true;
        }
        Class<?> beanType = handlerMethod.getBeanType();
        RequireLogin annotation = beanType.getAnnotation(RequireLogin.class);
        RequireLogin methodAnnotation = handlerMethod.getMethodAnnotation(RequireLogin.class);
        if(annotation == null && methodAnnotation == null){
            return true;
        }
        String token = request.getHeader("token");
        JwtUtils.verifyToken(token);
        int userId = (int) JwtUtils.getClaim(token).get("id");
        LoginUser loginUser = redisCache.getCacheObject("USER:LOGIN:" + userId);
        long currentTime = System.currentTimeMillis();
        if(loginUser == null || loginUser.getExpireTime() > currentTime){
            throw new BussinessException("token已失效！");
        }else if(loginUser.getExpireTime() - currentTime < 5 * 1000 * 60){
            //刷新token,重新放入redis中
            loginUser.setLoginTime(currentTime);
            loginUser.setExpireTime(currentTime +  30 * 60 * 1000);
            redisCache.setCacheObject("USER:LOGIN:"+loginUser.getId(),loginUser,30L, TimeUnit.MINUTES);
        }
        return true;
    }
}
