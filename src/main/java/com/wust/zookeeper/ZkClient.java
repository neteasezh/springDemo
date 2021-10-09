package com.wust.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

@Slf4j
public class ZkClient {

    private static ZooKeeper zooKeeper;
    private static String host = "192.168.0.105:2181";
    private static int time = 100000;

    @BeforeAll
    public static void init() throws Exception {
        zooKeeper = new ZooKeeper(host, time, watchedEvent -> {
           /* //监控子节点的增加
            try {
                List<String> children = zooKeeper.getChildren("/", true);
                System.out.println("=========================");
                System.out.println(children);
                System.out.println("=========================");
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        });
        /*//创建节点
        String s = zooKeeper.create("/node", "hello,zookeeper".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(s);*/
       /* List<String> children = zooKeeper.getChildren("/", true);
        System.out.println(children + "-------------------------");
        while (true);*/
    }

    @Test
    public void testIsExist() throws InterruptedException, KeeperException {
        Stat stat = zooKeeper.exists("/node", false);
        System.out.println(stat);
        System.out.println(stat == null ? "not exist":"exist");
    }
}
