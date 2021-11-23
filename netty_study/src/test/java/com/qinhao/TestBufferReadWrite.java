package com.qinhao;

import java.nio.Buffer;
import java.nio.ByteBuffer;

import static com.qinhao.ByteBufferUtil.debugAll;

/**
 * describ
 *
 * @author qinhao
 * @date 2021/11/22 - 22:07
 */
public class TestBufferReadWrite {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        //  ByteBuffer buffer = ByteBuffer.allocateDirect(10);
        // 操作直接内存，效率高，不会受gc影响，分配效率低，可能内存泄漏
        buffer.put((byte) 97);
        debugAll(buffer);
        System.out.println(buffer.get());
        buffer.flip();
        System.out.println(buffer.get());
        debugAll(buffer);
        buffer.compact();
        debugAll(buffer);
        //从头读取
        buffer.rewind();
        //mark reset 增强rewind
        buffer.mark();
        //get(i)不会改变索引位置
    }
}
