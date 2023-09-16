package com.wolfnode.wolf2w.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wolfnode.wolf2w.core.exception.BussinessException;
import com.wolfnode.wolf2w.core.utils.JwtUtils;
import com.wolfnode.wolf2w.core.utils.Md5Utils;
import com.wolfnode.wolf2w.redis.utils.RedisCache;
import com.wolfnode.wolf2w.user.domain.UserInfo;
import com.wolfnode.wolf2w.user.mapper.UserMapper;
import com.wolfnode.wolf2w.user.service.UserService;
import com.wolfnode.wolf2w.user.vo.LoginUser;
import com.wolfnode.wolf2w.user.vo.UserRegisterRequest;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author zhouyihang
 * @version 1.0
 * @description: TODO
 * @date 2023/9/14 13:51
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserInfo> implements UserService {

    @Autowired
    private RedisCache redisCache;

    @Override
    public UserInfo checkPhoneExists(String phone) {
        LambdaQueryWrapper lambdaQueryWrapper = new LambdaQueryWrapper<UserInfo>().eq(UserInfo::getPhone,phone);
        return getOne(lambdaQueryWrapper);
    }

    @Override
    public void register(UserRegisterRequest userRegisterRequest) {
        //校验用户名手机号
        //检查验证码是否匹配
        Object verifyCode = redisCache.getCacheObject("USER:REGISTER:VERIFY_CODE:" + userRegisterRequest.getPhone());
        if(verifyCode == null || !verifyCode.equals(userRegisterRequest.getVerifyCode())){
            throw new BussinessException("验证码错误！");
        }
        //删除redis中的key
        redisCache.deleteObject("USER:REGISTER:VERIFY_CODE:" + userRegisterRequest.getPhone());
        //插入数据库
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userRegisterRequest,userInfo);
        //密码加密
        String md5Password = Md5Utils.getMD5(userInfo.getPassword() + userInfo.getPhone());
        userInfo.setPassword(md5Password);
        userInfo.setHeadImgUrl("/images/default.jpg");
        save(userInfo);
    }

    @Override
    public Map<String, Object> login(String phone, String password) {
        //校验用户
        UserInfo userInfo = this.checkPhoneExists(phone);
        if(userInfo == null){
            throw new BussinessException(500100,"账号或密码错误！");
        }
        //校验密码
        String md5Password = Md5Utils.getMD5(password + phone);
        if(!userInfo.getPassword().equalsIgnoreCase(md5Password)){
            throw new BussinessException(500100,"账号或密码错误！");
        }

        LoginUser loginUser = new LoginUser();
        BeanUtils.copyProperties(userInfo,loginUser);
        long currentTimeMillis = System.currentTimeMillis();
        loginUser.setLoginTime(currentTimeMillis);
        //30分钟过期时间
        loginUser.setExpireTime(currentTimeMillis + 30 * 60 * 1000);
        //放入redis
        redisCache.setCacheObject("USER:LOGIN:"+loginUser.getId(),loginUser,30L, TimeUnit.MINUTES);
        //生成token
        Map<String,Object> payload = new HashMap<>();
        payload.put("id",userInfo.getId());
        String token = JwtUtils.getToken(payload);

        payload.clear();
        payload.put("token",token);
        userInfo.setPassword(null);
        payload.put("user",userInfo);
        return payload;
    }
}
