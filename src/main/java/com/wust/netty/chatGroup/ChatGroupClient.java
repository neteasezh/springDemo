package com.wust.netty.chatGroup;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Scanner;

public class ChatGroupClient {
    private static final String HOST = "localhost";
    private static final int PORT = 8888;

    public static void run() throws InterruptedException {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();

        bootstrap.group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        pipeline.addLast("decoder", new StringDecoder());
                        pipeline.addLast("encoder", new StringEncoder());
                        pipeline.addLast(new ChatGroupClientHandler());
                    }
                });
        ChannelFuture future = bootstrap.connect(HOST, PORT).sync();
        System.out.println("client started.....");

        Channel channel = future.channel();
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()){
            String s = sc.nextLine();
            channel.writeAndFlush(s + "\r\n");
        }
        channel.closeFuture().sync();
        eventLoopGroup.shutdownGracefully();
    }
    public static void main(String[] args) throws InterruptedException {
        ChatGroupClient chatGroupClient = new ChatGroupClient();
        chatGroupClient.run();
    }
}
