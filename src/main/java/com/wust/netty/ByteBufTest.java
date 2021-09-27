package com.wust.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class ByteBufTest {
    public static void main(String[] args) {
        ByteBuf buffer = Unpooled.buffer(10);
        System.out.println(buffer.readableBytes());
        System.out.println(buffer.writableBytes());
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.writeByte(i);
        }
        for (int i = 0; i < buffer.capacity(); i++) {
            System.out.println(buffer.getByte(i));
        }
        System.out.println(buffer.readableBytes());
        System.out.println(buffer.writableBytes());
    }
}
