package com.qinhao.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient {
    public static void main(String[] args) throws InterruptedException {
        //客户端需要一个事件循环组
        EventLoopGroup eventExecutors = new NioEventLoopGroup();
        try {
            //创建客户端的启动对象
            Bootstrap bootstrap = new Bootstrap();
            //设置启动参数
            bootstrap.group(eventExecutors)//设置线程组
                .channel(NioSocketChannel.class)//设置客户端通道实现类
                .handler(new ChannelInitializer<SocketChannel>() {//创建一个通道初始化对象
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new NettyClientHandler());//加入自己的处理器
                    }
                });
            System.out.println("【客户端】准备完毕....");
            //指定客户端连接的服务器地址
            ChannelFuture cf = bootstrap.connect("127.0.0.1", 6668).sync();
            //对关闭通道进行监听
            cf.channel().closeFuture().sync();
        } finally {
            //优雅关闭
            eventExecutors.shutdownGracefully();
        }

    }
}