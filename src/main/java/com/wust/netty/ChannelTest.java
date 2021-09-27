package com.wust.netty;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

public class ChannelTest {
    public static void test1() throws Exception {
        String str = "hello,world";
        FileOutputStream fileOutputStream = new FileOutputStream("f://file1.txt");
        FileChannel channel = fileOutputStream.getChannel();
        //创建缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put(str.getBytes());
        buffer.flip();
        //将缓冲区中的数据写入通道
        channel.write(buffer);
        fileOutputStream.close();

    }

    public static void test2() throws Exception {
        File file = new File("f://file1.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        FileChannel channel = fileInputStream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate((int)file.length());
        channel.read(buffer);
        //buffer.flip();
        System.out.println(new String(buffer.array()));
        fileInputStream.close();
    }
    public static void test3() throws Exception {
        File from = new File("f://file1.txt");
        File to = new File("f://file2.txt");

        FileInputStream fileInputStream = new FileInputStream(from);
        FileChannel fromChannel = fileInputStream.getChannel();
        FileOutputStream fileOutputStream = new FileOutputStream(to);
        FileChannel toChannel = fileOutputStream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while(true){
            buffer.clear();
            int read = fromChannel.read(buffer);
            if(read == -1){
                //表示读取完毕
                break;
            }
            buffer.flip();
            toChannel.write(buffer);
        }
        fileInputStream.close();
        fileOutputStream.close();
    }
    public static void test4() throws Exception {
        File from = new File("f://1.png");
        File to = new File("f://2.png");
        FileInputStream fileInputStream = new FileInputStream(from);
        FileOutputStream fileOutputStream = new FileOutputStream(to);
        FileChannel source = fileInputStream.getChannel();
        FileChannel target = fileOutputStream.getChannel();
        long l = source.transferTo(0, source.size(), target);
        fileInputStream.close();
        fileOutputStream.close();
    }
    public static void test5() throws Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(7000);
        //绑定端口到socket并启动
        serverSocketChannel.bind(inetSocketAddress);
        ByteBuffer[] byteBuffers = new ByteBuffer[2];
        byteBuffers[0] = ByteBuffer.allocate(5);
        byteBuffers[1] = ByteBuffer.allocate(3);

        //等待客户端连接
        SocketChannel socketChannel = serverSocketChannel.accept();

        int len = 8;//指定从客户端接受8个字节
        while(true){
            int r = 0;
            while (r < len){
                long l = socketChannel.read(byteBuffers);
                r += l;
                System.out.println("读取了" + r + "个字节" );
                for (ByteBuffer byteBuffer : byteBuffers) {
                    System.out.println("读取的内容为：" + new String(byteBuffer.array()));
                }
                Arrays.asList(byteBuffers).stream().map(byteBuffer -> "position = " +
                        byteBuffer.position() + ", limit = " + byteBuffer.limit()).forEach(System.out::println);
                //进行反转操作
                Arrays.asList(byteBuffers).forEach(byteBuffer -> byteBuffer.flip());

                long w = 0;
                while(w < len){
                    long write = socketChannel.write(byteBuffers);
                    w += write;
                }

                //将所有buffer的标志位重置
                Arrays.asList(byteBuffers).forEach(byteBuffer -> byteBuffer.clear());
                System.out.println("r = " + r + ", w = " + w + ", len = " + len);
            }
        }
    }
    public static void main(String[] args) {
        try {
//            test1();
//            test2();
//            test3();
//            test4();
            test5();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
