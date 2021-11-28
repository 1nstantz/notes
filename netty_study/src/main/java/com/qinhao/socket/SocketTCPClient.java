package com.qinhao.socket;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * describ
 *
 * @author qinhao
 * @date 2021/11/27 - 10:57
 */
public class SocketTCPClient {
    public static void main(String[] args) throws IOException {

        //连接这个地址的9999端口
        Socket socket = new Socket(InetAddress.getLocalHost(), 6666);
        System.out.println("客户端连接");
        OutputStream outputStream = socket.getOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        bufferedWriter.write("hello sever");
        bufferedWriter.newLine();
        bufferedWriter.flush();

        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String s = bufferedReader.readLine();
        System.out.println(s);


        bufferedReader.close();
        bufferedWriter.close();
        socket.close();
        System.out.println("客户端退出");
    }
}
