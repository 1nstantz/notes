package com.qinhao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author qinhao
 * @date 2021/11/26 - 11:34
 */
public class NIOFileChannel2 {
    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream = new FileInputStream("1.txt");
        FileChannel channel = fileInputStream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(32);
        channel.read(buffer);
        //将 byteBuffer 的字节数据转成 String
        System.out.println(new String(buffer.array()));
        fileInputStream.close();
    }
}
