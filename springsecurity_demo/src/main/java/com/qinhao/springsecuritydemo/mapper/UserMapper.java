package com.qinhao.springsecuritydemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qinhao.springsecuritydemo.pojo.Users;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author qinhao
 * @date 2022/7/1 - 17:26
 */
@Mapper
public interface UserMapper extends BaseMapper<Users>{

}
