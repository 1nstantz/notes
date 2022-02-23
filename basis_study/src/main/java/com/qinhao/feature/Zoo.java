package com.qinhao.feature;

import lombok.Data;

import java.util.Optional;

/**
 * describ
 *
 * @author qinhao
 * @date 2022/2/13 - 20:43
 */
@Data
public class Zoo {
    private Dog dog;
    public static void main(String[] args) {
        Zoo zoo = new Zoo();

        if (zoo != null) {
            Dog dog = zoo.getDog();
            if (dog != null) {
                int age = dog.getAge();
                System.out.println(age);
            }
        }

        //java8判空
        Optional.ofNullable(zoo).map(o -> o.getDog()).map(d -> d.getAge()).ifPresent(age ->
                System.out.println(age)
        );
    }
}

@Data
class Dog {
    private int age;
}




