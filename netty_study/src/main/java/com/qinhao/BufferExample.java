package com.qinhao;

import java.nio.IntBuffer;

/**
 * @author qinhao
 * @date 2021/11/26 - 10:53
 */
public class BufferExample {
    public static void main(String[] args) {
        IntBuffer buffer = IntBuffer.allocate(8);
        for (int i = 0; i < 4; i++) {
            buffer.put(i);
        }
        buffer.flip();
        while (buffer.hasRemaining()) {
            System.out.println(buffer.get());
        }
    }
}
