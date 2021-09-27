package com.wust.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

//将解码后的数据交给下一个handler
public class MyServerDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buf, List<Object> list) throws Exception {
        System.out.println("call the method decode");
        //有8个字节才有一个Long
        if(buf.readableBytes() >= 8){
            list.add(buf.readLong());
        }
    }
}
