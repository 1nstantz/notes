package com.qinhao.socket;

/**
 * describ
 *
 * @author qinhao
 * @date 2022/6/28 - 12:01
 */
public class Sing {
    private  static volatile Sing instance = null;
    private Sing() {
    }

    public  static  Sing getInstance() {
        if (instance == null) {
            synchronized (Sing.class) {
                if (instance == null) {
                    instance = new Sing();
                }
            }
        }
        return instance;
    }

}
