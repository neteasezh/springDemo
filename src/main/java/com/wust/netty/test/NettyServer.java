package com.wust.netty.test;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {
    public static void main(String[] args) throws InterruptedException {
        //创建一个bossGroup和一个workGroup

        //bossGroup仅处理连接请求
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //workerGroup处理读写的业务请求
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            //创建服务器端的启动的对象，配置参数
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,128)
                    .childOption(ChannelOption.SO_KEEPALIVE,true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        //向pipeLine中增加一个处理器
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new NettyServerHandler());
                        }
                    });

            System.out.println("the server is ready...");

            //绑定一个端口并且同步处理
            ChannelFuture cf = serverBootstrap.bind(6668).sync();
            cf.addListener((ChannelFutureListener) channelFuture -> {
               if(channelFuture.isSuccess()){
                   System.out.println("绑定端口成功...");
               }else {
                   System.out.println("绑定端口失败...");
               }
            });

            //对关闭通道进行监听
            cf.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
