package org.junhui.gump.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;

public class CacheBreakdownProtection {
    private final Map<String, CountDownLatchHandler<?>> map = new ConcurrentHashMap<>();

    public <R> R protect(String key, Integer awaitTimeout, Supplier<R> supplier) {
        if (!map.containsKey(key)) {
            synchronized (CacheBreakdownProtection.class) {
                if (!map.containsKey(key)) {
                    map.put(key, new CountDownLatchHandler<R>());
                }
            }
        }

        CountDownLatchHandler<R> handler = (CountDownLatchHandler<R>) map.get(key);
        if (handler.tryLock()) {
            try {
                handler.setValue(supplier.get());
            } catch (Exception e) {
                //log.error("CacheBreakdownProtection protect error", e);
            } finally {
                handler.countDown();
                map.remove(key);
            }
        } else {
            handler.await(awaitTimeout);
        }

        return handler.getValue();
    }


    private static class CountDownLatchHandler<T> {
        private T value;
        private final CountDownLatch countDownLatch = new CountDownLatch(1);
        private final Lock lock = new ReentrantLock();

        public boolean tryLock() {
            return lock.tryLock();
        }

        public void await(Integer awaitTimeout) {
            try {
                countDownLatch.await(awaitTimeout, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                //log.error("CountDownLatchHandler await error", e);
            }
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public void countDown() {
            countDownLatch.countDown();
        }
    }
}