package com.wust.netty.chatGroup;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ChatGroupServerHandler extends SimpleChannelInboundHandler<String> {
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static Map<Integer,Channel> map = new HashMap<>();

    //一旦连接建立第一个被执行
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("【"+channel.remoteAddress()+"+】加入聊天 " + sdf.format(new Date()));
        channelGroup.add(channel);
        map.put(channel.hashCode(),channel);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //提示上线
        System.out.println("【" + ctx.channel().remoteAddress() + "】上线了\n");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("【" + ctx.channel().remoteAddress() + "】 离线了\n");
    }

    //断开连接
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("【" + channel.remoteAddress() + "】离开了\n");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        Channel channel = channelHandlerContext.channel();

        channelGroup.forEach(ch -> {
            //排除自己
            if(channel != ch){
                ch.writeAndFlush("【" + channel.remoteAddress() + "】：" + s + "\n");
            }else{
                //自己则回显
                ch.writeAndFlush("【自己】：" + s + "\n");
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //有异常就关闭
        ctx.close();
    }
}
