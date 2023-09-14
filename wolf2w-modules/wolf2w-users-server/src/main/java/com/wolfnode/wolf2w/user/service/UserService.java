package com.wolfnode.wolf2w.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wolfnode.wolf2w.user.domain.UserInfo;

public interface UserService extends IService<UserInfo> {


    /**
     * 检查电话是否存在
     *
     * @param phone 电话
     * @return {@link UserInfo}
     */
    UserInfo checkPhoneExists(String phone);
}
