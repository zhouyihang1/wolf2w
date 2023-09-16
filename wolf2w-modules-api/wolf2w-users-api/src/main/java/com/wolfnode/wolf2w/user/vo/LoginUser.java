package com.wolfnode.wolf2w.user.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author zhouyihang
 * @version 1.0
 * @description: TODO
 * @date 2023/9/16 17:00
 */
@Data
public class LoginUser {

    private Long id;
    private String nickname;
    private String phone;
    private String email;
    private Integer gender;
    private Integer level = 0;
    private String city;
    private String headImgUrl;
    private String info;
    private Integer state;
    private Long loginTime;
    private Long expireTime;

}
