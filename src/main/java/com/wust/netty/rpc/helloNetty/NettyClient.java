package com.wust.netty.rpc.helloNetty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import java.lang.reflect.Proxy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NettyClient {
    //创建线程池
    private static ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private static NettyClientHandler clientHandler;

    //获取代理对象
    public Object getProxy(final Class<?> clazz, final String head){
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class<?>[]{clazz}, (proxy, method, args) -> {
            if (clientHandler == null){
                initClient();
            }
            System.out.println("args[0] = "+args[0]);
            clientHandler.setParam(head + args[0]);
            return executor.submit(clientHandler).get();
        });
    }

    public void initClient() throws InterruptedException {
        clientHandler = new NettyClientHandler();
        EventLoopGroup group = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY,true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();

                        pipeline.addLast(new StringDecoder());
                        pipeline.addLast(new StringEncoder());
                        pipeline.addLast(clientHandler);
                    }
                });
        System.out.println("开始建立连接");
        ChannelFuture future = bootstrap.connect("127.0.0.1", 8888).sync();
        System.out.println("建立连接成功");

        future.channel().closeFuture().sync();
        group.shutdownGracefully();
    }
}
