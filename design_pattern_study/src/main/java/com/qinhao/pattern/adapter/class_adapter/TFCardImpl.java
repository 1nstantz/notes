package com.qinhao.pattern.adapter.class_adapter;

/**
 * @version v1.0
 * @ClassName: TFCardImpl
 * @Description: 适配者类
 * @Author: qh
 */
public class TFCardImpl implements TFCard {

    @Override
    public String readTF() {
        String msg = "TFCard read msg ： hello word TFcard";
        return msg;
    }

    @Override
    public void writeTF(String msg) {
        System.out.println("TFCard write msg :" + msg);
    }
}
