package com.wust.zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DistributedClient {
    private ZooKeeper zooKeeper;
    public static void main(String[] args) {
        //获取zk连接
        DistributedClient client = new DistributedClient();
        client.getConnection();
        //监听servers下面的子节点
        client.getServerList();
        //业务逻辑
        client.business();
    }

    private void business() {
        try {
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void getServerList() {
        try {
            List<String> children = zooKeeper.getChildren("/servers", true);
            List<String> servers = new ArrayList<>();
            children.forEach(s -> {
                try {
                    byte[] data = zooKeeper.getData("/servers/" + s, false, null);
                    servers.add(new String(data));
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });
            System.out.println(servers);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void getConnection() {
        try {
                zooKeeper = new ZooKeeper("192.168.0.105:2181,192.168.0.106:2181", 100000, watchedEvent -> {
                    //监听触发时再次监听
                    getServerList();
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
