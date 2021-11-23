package com.qinhao;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * describ
 *
 * @author qinhao
 * @date 2021/11/20 - 13:02
 */
@Slf4j
public class TestByteBuffer {
    public static void main(String[] args) {
        //filechannel
        //1.输入输出流 2、randomAccessfile
        try {
            FileChannel channel = new FileInputStream("D:/github/notes/netty_study/data.txt").getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(10);//缓冲区的容量
            //写入缓冲区
            while (true){
                int len = channel.read(buffer);
                log.info("读到的字节数{}",len);
                if (len == -1) {
                    break;
                }
                //打印
                buffer.flip();//切换至读模式
                while (buffer.hasRemaining()) {//是否还有剩余未读数
                    byte b = buffer.get();
                    log.info("实际字节{}",(char) b);
                }
                buffer.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {

        }
    }
}
