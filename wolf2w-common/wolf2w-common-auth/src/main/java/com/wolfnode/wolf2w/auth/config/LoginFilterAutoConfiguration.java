package com.wolfnode.wolf2w.auth.config;

import com.wolfnode.wolf2w.auth.filter.LoginInterceptor;
import com.wolfnode.wolf2w.redis.utils.RedisCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhouyihang
 * @version 1.0
 * @description: TODO
 * @date 2023/9/16 17:45
 */
@Configuration
public class LoginFilterAutoConfiguration {
    @Bean
    public LoginInterceptor loginInterceptor(RedisCache redisCache){
        return new LoginInterceptor(redisCache);
    }
}
