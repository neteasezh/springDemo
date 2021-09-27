package com.wust.netty;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {
    public static void main(String[] args) throws Exception {
        //创建socket
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //获得selector
        Selector selector = Selector.open();
        //绑定端口
        serverSocketChannel.socket().bind(new InetSocketAddress(6666));
        //设置为非阻塞
        serverSocketChannel.configureBlocking(false);
        //将serverSocketChannel注册到selector中
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        //循环获取客户端连接
        while(true){
            //如果一秒内没有事件发生就返回
            if(selector.select(1000) == 0){
                System.out.println("服务器等待了1秒，无连接");
                continue;
            }
            //如果有事件发生,获得相关的SelectionKey集合
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while(iterator.hasNext()){
                //获得SelectionKey
                SelectionKey selectionKey = iterator.next();
                //有客户端的连接事件
                if(selectionKey.isAcceptable()){
                    //给客户端生成SocketChannel
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    System.out.println("客户端连接成功");
                    //将SocketChannel设置为非阻塞，否则在注册时会报错
                    socketChannel.configureBlocking(false);
                    //将socketChannel注册到selector中,并关联一个ByteBuffer
                    socketChannel.register(selector,SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }
                if (selectionKey.isReadable()){
                    //通过selectionKey获得channel
                   SocketChannel channel = (SocketChannel) selectionKey.channel();
                    //获取到channel关联的buffer
                    ByteBuffer buffer = (ByteBuffer)selectionKey.attachment();
                    channel.read(buffer);
                    System.out.println("from client: " + new String(buffer.array()));
                }
                //手动移除SelectionKey,防止重复操作
                iterator.remove();
            }
        }
    }
}
