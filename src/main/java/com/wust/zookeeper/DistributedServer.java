package com.wust.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

public class DistributedServer {
    public static void main(String[] args) {
        //1、获取zk连接
        DistributedServer server = new DistributedServer();
        ZooKeeper zooKeeper = server.getConnection();
        //2、注册服务器到zk集群
        server.register(zooKeeper,args[0]);
        //3、启动业务逻辑
        server.business();
    }

    private void business() {
        try {
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void register(ZooKeeper zooKeeper,String host) {
        try {
            String s = zooKeeper.create("/servers/" + host, host.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            System.out.println(host + " is online!");
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private ZooKeeper getConnection() {
        ZooKeeper zooKeeper = null;
        try {
             zooKeeper = new ZooKeeper("192.168.0.105:2181,192.168.0.106:2181", 100000, watchedEvent -> {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return zooKeeper;
    }
}
