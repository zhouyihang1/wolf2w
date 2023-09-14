package com.wolfnode.wolf2w.user.service;

public interface SmsService {

    /**
     * 注册短信发送
     *
     * @param phone 电话
     */
    void registerSmsSend(String phone);
}
