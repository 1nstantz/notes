package com.qinhao;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * describ
 *
 * @author qinhao
 * @date 2021/11/22 - 22:30
 */
public class TestByteBufferToString {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(16);
        buffer.put("hello".getBytes());

        ByteBuffer buffer2 = StandardCharsets.UTF_8.encode("hello");

        ByteBuffer.wrap("hello".getBytes());

        
    }
}
