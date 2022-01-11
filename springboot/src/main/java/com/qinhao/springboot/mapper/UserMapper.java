package com.qinhao.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qinhao.springboot.pojo.User;
import org.springframework.stereotype.Repository;

/**
 * @author qinhao
 * @date 2022/1/11 - 9:59
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

}
