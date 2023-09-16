package com.wolfnode.wolf2w.user.controller;

import com.wolfnode.wolf2w.auth.anno.RequireLogin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhouyihang
 * @version 1.0
 * @description: TODO
 * @date 2023/9/16 17:41
 */
@RestController
public class TestCotroller {


    @GetMapping("/test1")
    @RequireLogin
    public String test(){
        return "success1";
    }

    @GetMapping("/test2")
    public String test2(){
        return "success2";
    }
}
