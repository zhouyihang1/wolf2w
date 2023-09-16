package com.wolfnode.wolf2w.user.vo;

import lombok.Data;

/**
 * @author zhouyihang
 * @version 1.0
 * @description: TODO
 * @date 2023/9/15 21:52
 */
@Data
public class UserRegisterRequest {
    private String phone;
    private String nickname;
    private String password;
    private String verifyCode;
}
