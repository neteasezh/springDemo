package com.wust.netty.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

public class HttpServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        //向管道加入处理器
        ChannelPipeline pipeline = socketChannel.pipeline();

        //加入一个netty提供的HttpServerCodec
        pipeline.addLast("myHttpServerCodec",new HttpServerCodec());

        //加一个自己自定义的处理器
        pipeline.addLast(new HttpHandler());
    }
}
