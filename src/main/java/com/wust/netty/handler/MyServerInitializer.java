package com.wust.netty.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class MyServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();

        //加入handler
        //增加入站的解码器
        pipeline.addLast(new MyServerDecoder());

        pipeline.addLast(new HandlerEncoder());
        //添加自定义handler处理接收的数据
        pipeline.addLast(new MyServerHandler());
    }
}
