package com.qinhao.springboot.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import javax.validation.constraints.NotNull;

/**
 * @author qinhao
 * @date 2022/1/11 - 9:46
 */
@Data
@TableName("user")
@Accessors(chain = true)
@AllArgsConstructor
public class User {
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private Long id;

    @NotNull(message = "姓名不能为空")
    private String name;
    private Integer age;

    public static void main(String[] args) {
        System.out.println(1.0/2.0);
    }
}
