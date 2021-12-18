package com.qinhao.pattern.template;

/**
 * @version v1.0
 * @ClassName: ConcreteClass_BaoCai
 * @Description: 炒包菜类
 * @Author: qh
 */
public class ConcreteClass_BaoCai extends AbstractClass {

    public void pourVegetable() {
        System.out.println("下锅的蔬菜是包菜");
    }

    public void pourSauce() {
        System.out.println("下锅的酱料是辣椒");
    }
}
