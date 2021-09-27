package com.wust.netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

public class HttpHandler extends SimpleChannelInboundHandler<HttpObject> {

    //当有读取事件发生时触发
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpObject httpObject) throws Exception {
        if(httpObject instanceof HttpRequest){
            System.out.println("httpObject类型： " + httpObject.getClass());
            System.out.println("客户端地址： " + channelHandlerContext.channel().remoteAddress());

            HttpRequest request = (HttpRequest) httpObject;
            System.out.println(request.uri());
            if(request.uri().equals("/favicon.ico")){
                return;
            }


            System.out.println("pipeline : " + channelHandlerContext.pipeline().hashCode());
            System.out.println("HttpHandler : " + this.hashCode());

            //response to client
            ByteBuf buf = Unpooled.copiedBuffer("hello,我是服务器", CharsetUtil.UTF_8);

            //构造一个http的响应
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, buf);

            response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain;charset= utf-8");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH,buf.readableBytes());
            //response.headers().set(HttpHeaderNames.CONNECTION,"keep-alive");

            //return the response
            channelHandlerContext.writeAndFlush(response);
        }
    }
}
