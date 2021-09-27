package com.wust.netty.rpc.provider;

import com.wust.netty.rpc.HelloService;

public class HelloServiceImpl implements HelloService {
    @Override
    public String hello(String mes) {
        System.out.println("收到客户端消息：" + mes);
        if(mes != null){
            return "hello,客户端";
        }else {
            return "收到的消息为空";
        }

    }
}
