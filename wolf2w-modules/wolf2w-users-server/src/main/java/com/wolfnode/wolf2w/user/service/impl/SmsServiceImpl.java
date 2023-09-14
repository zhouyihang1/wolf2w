package com.wolfnode.wolf2w.user.service.impl;

import com.wolfnode.wolf2w.redis.utils.RedisCache;
import com.wolfnode.wolf2w.user.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author zhouyihang
 * @version 1.0
 * @description: TODO
 * @date 2023/9/14 23:04
 */

@Service
public class SmsServiceImpl implements SmsService {

    @Autowired
    private RedisCache redisCache;

    @Override
    public void registerSmsSend(String phone) {
        //手机号校验（不做）
        //生成验证码
        String code = generateCode();
        //保存到redis
        redisCache.setCacheObject("USER:REGISTER:VERIFY_CODE:"+phone,code,30L, TimeUnit.MINUTES);
        System.out.println("生成验证码"+code);

        //调用第三方接口

    }

    private String generateCode() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 4);
    }
}
