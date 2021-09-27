package com.wust.netty.tcp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.StandardCharsets;

public class TcpServerHandler extends SimpleChannelInboundHandler<Message> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message message) throws Exception {
        int len = message.getLen();
        byte[] content = message.getContent();

        System.out.println("the msg from client is : " + new String(content));

        String res = "客户端你好，接收到了消息！";
        byte[] bytes = res.getBytes(StandardCharsets.UTF_8);
        Message m = new Message(bytes.length, bytes);
        ctx.writeAndFlush(m);
    }
}
