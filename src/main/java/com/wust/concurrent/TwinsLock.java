package com.wust.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class TwinsLock implements Lock {
    static class Sync extends AbstractQueuedSynchronizer {
        Sync(int count){
            if(count <= 0){
                throw new IllegalArgumentException("count must be larger than 0");
            }
            setState(count);
        }

        @Override
        public boolean tryReleaseShared(int reduceCount) {
            for(;;){
                int current = getState();
                int newCount = current - reduceCount;
                if(newCount < 0){
                    return false;
                }else {
                    return compareAndSetState(current,newCount);
                }
            }
        }
    }
    @Override
    public void lock() {

    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {

    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
