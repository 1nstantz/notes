package com.qinhao.pattern.mediator;

/**
 * @version v1.0
 * @ClassName: Person
 * @Description: 抽象同事类
 * @Author: qh
 */
public abstract class Person {

    protected String name;
    protected Mediator mediator;

    public Person(String name, Mediator mediator) {
        this.name = name;
        this.mediator = mediator;
    }
}
