package com.wust.algorithm;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;

public class LFUCache {
    //缓存容量，时间戳
    private static int capacity,time;
    private static Map<Integer,Node> key_table;
    private static TreeSet<Node> treeSet;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.time = 0;
        this.key_table = new HashMap<>();
        this.treeSet = new TreeSet<>();
    }

    public int get(int key){
        if(capacity == 0){
            return -1;
        }
        if(!key_table.containsKey(key)){
            return -1;
        }
        Node node = key_table.get(key);
        treeSet.remove(node);
        node.cnt += 1;
        node.time = ++time;
        treeSet.add(node);
        key_table.put(key,node);
        return node.value;
    }

    public static void put(int key, int value){
        if(capacity == 0){
            return;
        }
        if(!key_table.containsKey(key)){
            if(key_table.size() == capacity){
                key_table.remove(treeSet.first().key);
                treeSet.remove(treeSet.first());
            }
            Node node = new Node(1, ++time, key, value);
            key_table.put(key,node);
            treeSet.add(node);
        }else {
            Node node = key_table.get(key);
            treeSet.remove(node);
            node.cnt += 1;
            node.time = ++time;
            node.value = value;
            treeSet.add(node);
            key_table.put(key,node);
        }
    }

    static class Node implements Comparable<Node>{
        int cnt,time,key,value;

        public Node(int cnt, int time, int key, int value) {
            this.cnt = cnt;
            this.time = time;
            this.key = key;
            this.value = value;
        }

        public boolean equals(Object object){
            if(this == object){
                return true;
            }
            if(object instanceof Node){
                Node node = (Node) object;
                return this.cnt == node.cnt && this.time == node.time;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(cnt, time);
        }

        @Override
        public int compareTo(Node node) {
            return cnt == node.cnt ? time - node.time : cnt - node.cnt;
        }
    }

    public static void main(String[] args) {
        LFUCache cache = new LFUCache(3);
        cache.put(1,2);
        cache.put(2,3);
        int i = cache.get(1);
        cache.put(3,4);
        cache.put(4,5);
        System.out.println(cache.get(2));
    }
}
