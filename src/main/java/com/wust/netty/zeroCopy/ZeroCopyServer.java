package com.wust.netty.zeroCopy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class ZeroCopyServer {
    public static void main(String[] args) throws IOException {
        InetSocketAddress inetSocketAddress = new InetSocketAddress(7001);
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(inetSocketAddress);
//        ServerSocket socket = serverSocketChannel.socket();
//        socket.bind(inetSocketAddress);
        File file = new File("f://file11.txt");
        FileChannel channel = new FileOutputStream(file).getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(4096);
        while(true){
            SocketChannel socketChannel = serverSocketChannel.accept();
            int count = 0;
            while(count != -1){
                count = socketChannel.read(buffer);
                buffer.flip();
                channel.write(buffer);
                buffer.clear();
            }
            //重置position和mark
            buffer.rewind();
        }
    }
}
