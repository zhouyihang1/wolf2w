package com.wolfnode.wolf2w.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wolfnode.wolf2w.user.domain.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zhouyihang
 * @version 1.0
 * @description: TODO
 * @date 2023/9/14 13:49
 */
@Mapper
public interface UserMapper extends BaseMapper<UserInfo> {
}
