package com.wust.netty.tcp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MessageEncoder extends MessageToByteEncoder<Message> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Message message, ByteBuf byteBuf) throws Exception {
        byteBuf.writeInt(message.getLen());
        byteBuf.writeBytes(message.getContent());
        System.out.println("MessageEncoder::encode");
    }
}
