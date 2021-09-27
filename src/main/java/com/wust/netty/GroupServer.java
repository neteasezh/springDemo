package com.wust.netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class GroupServer {
    private Selector selector;
    private ServerSocketChannel serverSocketChannel;
    private static final int PORT = 6667;

    public GroupServer() {
        //得到选择器
        try {
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            //绑定端口
            serverSocketChannel.socket().bind(new InetSocketAddress(PORT));
            //设置为非阻塞
            serverSocketChannel.configureBlocking(false);
            //注册到selector中
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public  void listen(){
        while(true){
            try {
                int count = selector.select();
                if(count > 0){
                    //获取selectionKeys
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while(iterator.hasNext()){
                        SelectionKey selectionKey = iterator.next();
                        if(selectionKey.isAcceptable()){
                            SocketChannel socketChannel = serverSocketChannel.accept();
                            //设置为非阻塞
                            socketChannel.configureBlocking(false);
                            //注册到selector中
                            socketChannel.register(selector,SelectionKey.OP_READ);
                            System.out.println(socketChannel.getRemoteAddress().toString().substring(1) + "上线了");
                            String msg = "[" + socketChannel.getRemoteAddress().toString().substring(1) + "] 上线了";
                            forward(msg,socketChannel);
                        }
                        //可读事件
                        if(selectionKey.isReadable()){
                            //读取数据并转发
                            readData(selectionKey);
                        }
                        //把当前SelectionKey删除,防止重复使用
                        iterator.remove();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void readData(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel)key.channel();
        //创建缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        try {
            int read = channel.read(buffer);
            if(read > 0){
                String msg = new String(buffer.array());
                System.out.println("from client : " + msg.trim());
                //转发消息给其他客户端
                forward(msg,channel);
            }
        } catch (IOException e) {
            System.out.println(channel.getRemoteAddress().toString().substring(1) + "离线了...");
            String msg = "[" + channel.getRemoteAddress().toString().substring(1) + "] 离线了";
            forward(msg,channel);
            //取消注册
            key.cancel();
            channel.close();
        }
    }

    //转发时排除自己
    public void forward(String msg, SocketChannel self){
        for (SelectionKey key : selector.keys()) {
            //通过key取出通道
            Channel targetChannel =  key.channel();
            if(targetChannel instanceof SocketChannel && targetChannel != self){
                //将msg存储到buffer中
                ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
                try {
                    SocketChannel channel = (SocketChannel) targetChannel;
                    channel.write(buffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void main(String[] args) {
        GroupServer groupServer = new GroupServer();
        groupServer.listen();
    }
}
