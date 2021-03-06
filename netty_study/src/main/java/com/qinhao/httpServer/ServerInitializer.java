package com.qinhao.httpServer;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

public class ServerInitializer extends ChannelInitializer<SocketChannel> {
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel
            .pipeline()
            .addLast("myHttpServerCodec",new HttpServerCodec())
            .addLast(new HttpServerHandler());
    }
}