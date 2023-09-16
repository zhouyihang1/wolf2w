package com.wolfnode.wolf2w.auth.config;

import com.wolfnode.wolf2w.auth.filter.LoginInterceptor;
import com.wolfnode.wolf2w.redis.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author zhouyihang
 * @version 1.0
 * @description: TODO
 * @date 2023/9/16 17:22
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {


    @Autowired
    private LoginInterceptor loginInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(loginInterceptor)
               .addPathPatterns("/**");
    }
}
