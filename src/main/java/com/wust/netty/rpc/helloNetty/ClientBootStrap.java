package com.wust.netty.rpc.helloNetty;

import com.wust.netty.rpc.HelloService;

public class ClientBootStrap {
    //协议头
    private static final String HEAD = "HelloService#hello#";

    public static void main(String[] args) {
        NettyClient nettyClient = new NettyClient();

        HelloService service = (HelloService) nettyClient.getProxy(HelloService.class, HEAD);

        String res = service.hello("你好，server");
        System.out.println("调用的结果为：" + res);
    }
}
