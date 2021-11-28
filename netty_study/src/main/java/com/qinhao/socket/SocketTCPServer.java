package com.qinhao.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * describ
 *
 * @author qinhao
 * @date 2021/11/27 - 10:54
 */
public class SocketTCPServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(6666);
        Socket socket = serverSocket.accept();
        System.out.println("有一个连接");
        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String s = bufferedReader.readLine();
        System.out.println(s);

        OutputStream outputStream = socket.getOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        bufferedWriter.write("hello client");
        bufferedWriter.newLine();
        bufferedWriter.flush();


        bufferedWriter.close();
        bufferedReader.close();
        socket.close();
        System.out.println("服务端退出");
    }
}
