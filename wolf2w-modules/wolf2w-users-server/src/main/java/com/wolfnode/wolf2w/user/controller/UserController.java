package com.wolfnode.wolf2w.user.controller;

import com.wolfnode.wolf2w.core.utils.R;
import com.wolfnode.wolf2w.user.service.UserService;
import com.wolfnode.wolf2w.user.vo.UserRegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("/login")
    public R<?> login(String username,String password){
        return R.ok(userService.login(username,password));
    }

    @GetMapping("/phone/exists")
    public R<?> phoneExists(String phone){
        return R.ok(userService.checkPhoneExists(phone) != null);
    }

    @PostMapping("/register")
    public R<?> register(UserRegisterRequest userRegisterRequest){
        userService.register(userRegisterRequest);
        return R.ok();
    }
}
