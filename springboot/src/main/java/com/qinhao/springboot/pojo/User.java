package com.qinhao.springboot.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author qinhao
 * @date 2022/1/11 - 9:46
 */
@Data
@TableName("user")
@Accessors(chain = true)
public class User {
    @TableId(value = "id",type = IdType.ASSIGN_UUID)
    private Long id;
    private String name;
    private Integer age;
}
