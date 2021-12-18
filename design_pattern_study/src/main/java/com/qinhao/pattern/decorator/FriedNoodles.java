package com.qinhao.pattern.decorator;

/**
 * @version v1.0
 * @ClassName: FriedNoodles
 * @Description: 炒面(具体的构件角色)
 * @Author: qh
 */
public class FriedNoodles extends FastFood {

    public FriedNoodles() {
        super(12,"炒面");
    }

    @Override
    public float cost() {
        return getPrice();
    }
}
