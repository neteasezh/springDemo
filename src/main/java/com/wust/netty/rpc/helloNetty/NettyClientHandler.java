package com.wust.netty.rpc.helloNetty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.Callable;

public class NettyClientHandler extends ChannelInboundHandlerAdapter implements Callable {

    private ChannelHandlerContext context;
    private String res;
    private String param;

    //连接成功
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        this.context = ctx;
    }

    @Override
    public synchronized void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        res = msg.toString();
        notify();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    //被代理对象调用，发送数据给服务器，然后就等待被唤醒返回结果
    @Override
    public synchronized Object call() throws Exception {
        context.writeAndFlush(param);
        //等到客户端收到结果后被唤醒
        wait();
        return res;
    }

    public void setParam(String param) {
        this.param = param;
    }
}
