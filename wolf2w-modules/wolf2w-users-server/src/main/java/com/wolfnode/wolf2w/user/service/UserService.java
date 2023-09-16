package com.wolfnode.wolf2w.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wolfnode.wolf2w.user.domain.UserInfo;
import com.wolfnode.wolf2w.user.vo.UserRegisterRequest;

import java.util.Map;

public interface UserService extends IService<UserInfo> {


    /**
     * 检查电话是否存在
     *
     * @param phone 电话
     * @return {@link UserInfo}
     */
    UserInfo checkPhoneExists(String phone);

    /**
     * 用户注册
     *
     * @param userRegisterRequest 用户注册请求
     */
    void register(UserRegisterRequest userRegisterRequest);

    /**
     * 登录
     *
     * @param phone    电话
     * @param password 暗语
     * @return {@link Map}<{@link String},{@link Object}>
     */
    Map<String,Object> login(String phone, String password);
}
