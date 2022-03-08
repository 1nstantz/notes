package com.qinhao;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author qinhao
 * @date 2021/11/26 - 11:25
 */
public class NIOFileChannel {
    public static void main(String[] args) throws Exception {
        //创建文件输出流
        FileOutputStream fileOutputStream = new FileOutputStream("1.txt");
        //通过输出流获取通道
        FileChannel channel = fileOutputStream.getChannel();
        //创建缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(32);
        //向缓冲区写入数据
        buffer.put("今天星期五".getBytes());
        //写模式切换为读模式
        buffer.flip();
        //从缓冲区读取数据
        channel.write(buffer);
        fileOutputStream.close();
    }
}
