package com.wust.netty.chatGroup;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class ChatGroupServer {
    private static final int PORT = 8888;

    public void run() throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup,workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG,128)
                .option(ChannelOption.SO_KEEPALIVE ,true)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        //增加一个解码器
                        pipeline.addLast("decoder",new StringDecoder());
                        //增加一个编码器
                        pipeline.addLast("encoder",new StringEncoder());
                        //加入自定义handler
                        pipeline.addLast(new ChatGroupServerHandler());
                    }
                });
        ChannelFuture future = serverBootstrap.bind(PORT).sync();
        //监听关闭事件
        future.channel().closeFuture().sync();

        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }
    public static void main(String[] args) throws InterruptedException {
        ChatGroupServer chatGroupServer = new ChatGroupServer();
        chatGroupServer.run();
    }
}
