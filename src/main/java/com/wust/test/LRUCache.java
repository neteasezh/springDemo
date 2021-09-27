package com.wust.test;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache<K,V> extends LinkedHashMap<K,V> {
    public static final int MAX = 3;

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > MAX;
    }

    LRUCache(){
        super(MAX,0.75f,true);
    }

    public static void main(String[] args) {
        LRUCache<Integer,String> lruCache = new LRUCache<>();
        lruCache.put(1,"a");
        lruCache.put(2,"b");
        lruCache.put(3,"c");
        lruCache.get(1);
        lruCache.put(4,"d");
        System.out.println(lruCache.keySet());

    }
}
