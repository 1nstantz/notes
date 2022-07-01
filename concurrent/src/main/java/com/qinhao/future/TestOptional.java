package com.qinhao.future;

import com.qinhao.stream.Person;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Optional;

/**
 * @author qinhao
 * @date 2022/5/19 - 9:38
 */
public class TestOptional {

    @Test
    public void test1() {
        ArrayList<Person> list = new ArrayList<>();
        Optional<Person> optional = newPerson();
        //isPresent() 判断一个 Optional 对象是否存在，里面写函数
        optional.ifPresent(System.out::println);
        //isPresent() 判断一个 Optional 对象是否存在,返回boolean
        boolean present = optional.isPresent();
        String s = null;
        String name = Optional.ofNullable(s).orElse("qinhao");
        System.out.println(name);
        String name1 = Optional.ofNullable(s).orElseGet(() -> "qinhao");
        System.out.println(name1);

    }

    private Optional<Person> newPerson() {
        //of() 创建一个非空的 Optional 对象
        //return Optional.of(new Person());

        //empty() 创建一个空的 Optional 对象
        //return Optional.empty();

        //ofNullable() 创建一个即可空又可非空的 Optional 对象
        return Optional.ofNullable(new Person());
    }
}
