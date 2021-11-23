package com.qinhao.sort;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * describ
 *
 * @author qinhao
 * @date 2021/11/23 - 22:20
 */
@Data
@AllArgsConstructor
public class Student implements Comparable<Student>{
    private String name;
    private int age;

    @Override
    public int compareTo(Student o) {
        return this.getAge()-o.getAge();
    }
}
