package com.wolfnode.wolf2w.user.controller;

import com.wolfnode.wolf2w.core.utils.R;
import com.wolfnode.wolf2w.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhouyihang
 * @version 1.0
 * @description: TODO
 * @date 2023/9/14 13:52
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/phone/exists")
    public Object phoneExists(String phone){
        return R.ok(userService.checkPhoneExists(phone) != null);
    }
}
