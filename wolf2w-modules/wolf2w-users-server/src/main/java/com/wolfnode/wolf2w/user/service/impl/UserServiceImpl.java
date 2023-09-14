package com.wolfnode.wolf2w.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wolfnode.wolf2w.user.domain.UserInfo;
import com.wolfnode.wolf2w.user.mapper.UserMapper;
import com.wolfnode.wolf2w.user.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author zhouyihang
 * @version 1.0
 * @description: TODO
 * @date 2023/9/14 13:51
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserInfo> implements UserService {
}
