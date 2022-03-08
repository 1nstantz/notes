package com.qinhao;


import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * describ
 *
 * 每个连接都要一个线程，没事情的时候会阻塞
 *
 * @author qinhao
 * @date 2021/11/25 - 23:10
 */
public class BIOserver {
    public static void main(String[] args) throws IOException {
        //创建一个线程池
//        ExecutorService threadPool = Executors.newCachedThreadPool();
        ExecutorService threadPool = new ThreadPoolExecutor(8, 8, 1, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());

        //创建服务器
        ServerSocket serverSocket = new ServerSocket(6666);
        System.out.println("服务启动");
        while (true) {
            //链接客户端
            Socket socket = serverSocket.accept();
            System.out.println("链接一个客户端");
            threadPool.execute(() -> handler(socket));
        }
    }

    static void handler(Socket socket) {
        byte[] bytes = new byte[1024];
        try {
            System.out.println("当前线程："+Thread.currentThread().getId());
            InputStream inputStream = socket.getInputStream();
            //循环读取
            while (true) {
                int read = inputStream.read(bytes);
                if (read != -1) {
                    System.out.println(new String(bytes, 0, read));
                } else {
                    System.out.println("读取完毕");
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            System.out.println("关闭连接");
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
