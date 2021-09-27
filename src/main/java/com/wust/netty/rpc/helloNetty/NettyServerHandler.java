package com.wust.netty.rpc.helloNetty;

import com.wust.netty.rpc.provider.HelloServiceImpl;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;

public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //获取客户端发送过来的消息，并调用相应服务将结果返回给客户端
        System.out.println("msg = " + msg);
        //客户端在调用服务器的api时需要定义一个协议
        //比如每次发送消息时都必须以某一个字符串开头
        if(msg.toString().startsWith("HelloService#hello#")){
            String res = new HelloServiceImpl().hello(msg.toString().substring(msg.toString().lastIndexOf("#") + 1));
            ctx.writeAndFlush(res);
        }else {
            ctx.writeAndFlush(Unpooled.copiedBuffer("协议头错误".getBytes(StandardCharsets.UTF_8)));
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
