package com.wust.netty.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class HandlerClientInitializer  extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        //加入一个出站的handler,进行编码
        pipeline.addLast(new HandlerEncoder());

        pipeline.addLast(new MyServerDecoder());
        //加入自定义的handler
        pipeline.addLast(new MyClientHandler());
    }
}
