package com.wolfnode.wolf2w.user.controller;

import com.wolfnode.wolf2w.core.utils.R;
import com.wolfnode.wolf2w.user.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhouyihang
 * @version 1.0
 * @description: TODO
 * @date 2023/9/14 22:58
 */
@RestController
@RequestMapping("/sms")
public class SmsController {

    @Autowired
    private SmsService smsService;

    @GetMapping("/register")
    public R<?> registerVerifyCode(String phone){
        smsService.registerSmsSend(phone);
        return R.ok();
    }

}
