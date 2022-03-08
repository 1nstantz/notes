package com.qinhao.groupChat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * describ
 *
 * @author qinhao
 * @date 2022/3/6 - 19:26
 */
public class ChatServer {
    private ServerSocketChannel listenChannel;
    private Selector selector;
    private static int port = 6667;

    public ChatServer() throws Exception {
        listenChannel = ServerSocketChannel.open();
        selector = Selector.open();
        listenChannel.bind(new InetSocketAddress(port));
        listenChannel.register(selector, SelectionKey.OP_ACCEPT);
        listenChannel.configureBlocking(false);
        listen();
    }

    public void listen() throws Exception {
        while (true) {
            int count = selector.select(2000);
            if (count > 0) {
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = keys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    if (key.isAcceptable()) {
                        SocketChannel sc = listenChannel.accept();
                        sc.register(selector, SelectionKey.OP_READ);
                        System.out.println(sc.getRemoteAddress()+"上线了");
                    }
                    if (key.isReadable()) {
                        readData(key);
                    }
                    //一定要移除  防止重复处理
                    iterator.remove();
                }
            } else {
                System.out.println("wait。。。。。");
            }
        }
    }

    private void readData(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel)key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int count = channel.read(buffer);
        if (count > 0) {
            String msg = new String(buffer.array());
            System.out.println("from 客户端:" + msg);
            sendMsgToOthers(msg, channel);
        }
    }

    private void sendMsgToOthers(String msg, SocketChannel name) throws IOException {
        Set<SelectionKey> selectionKeys = selector.selectedKeys();
        System.out.println("服务器转发消息");
        for (SelectionKey selectionKey : selectionKeys) {
            Channel channel = selectionKey.channel();
            if (channel != name && channel instanceof SocketChannel) {
                SocketChannel channel1 = (SocketChannel) channel;
                ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
                channel1.write(buffer);
            }
        }
    }

    public static void main(String[] args) throws IOException {


    }
}
