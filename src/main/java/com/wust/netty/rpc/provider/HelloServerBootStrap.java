package com.wust.netty.rpc.provider;

import com.wust.netty.rpc.helloNetty.NettyServer;

public class HelloServerBootStrap {
    public static void main(String[] args) throws InterruptedException {
        //启动服务
        NettyServer.startServer("localhost",8888);
    }
}
