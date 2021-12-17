package com.qinhao.pattern.adapter.class_adapter;

/**
 * @version v1.0
 * @ClassName: SDAdapterTF
 * @Description: 适配器类
 * @Author: qh
 */
public class SDAdapterTF extends TFCardImpl implements SDCard {

    @Override
    public String readSD() {
        System.out.println("adapter read tf card");
        return readTF();
    }

    @Override
    public void writeSD(String msg) {
        System.out.println("adapter write tf card");
        writeTF(msg);
    }
}
