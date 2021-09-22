package algorithm.lru;

import annotation.ThreadSafe;

import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * �̰߳�ȫ��LRU����
 * ͨ�� ConcurrentHashMap + ConcurrentLinkedQueue + ReadWriteLock + ScheduledExecutorServiceʵ���̰߳�ȫ�� LRU ����
 * ���Ҵ��й���ʱ��
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/9/22 22:11
 */
@ThreadSafe("ConcurrentHashMap + ConcurrentLinkedQueue + ReadWriteLock + ScheduledExecutorService")
public class LRUCache<K, V> {
    /**
     * ������������
     */
    private final int maxCapacity;

    private ConcurrentHashMap<K, V> cacheMap;
    private ConcurrentLinkedQueue<K> keys;
    /**
     * ��д��
     */
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock writeLock = readWriteLock.writeLock();
    private final Lock readLock = readWriteLock.readLock();

    private ScheduledExecutorService scheduledExecutorService;

    public LRUCache(int maxCapacity) {
        if (maxCapacity < 0) {
            throw new IllegalArgumentException("Illegal max capacity: " + maxCapacity);
        }
        this.maxCapacity = maxCapacity;
        cacheMap = new ConcurrentHashMap<>(maxCapacity);
        keys = new ConcurrentLinkedQueue<>();
        scheduledExecutorService = Executors.newScheduledThreadPool(3);
    }

    public V put(K key, V value, long expireTime) {
        // ��д��
        writeLock.lock();
        try {
            // 1.key�Ƿ�����ڵ�ǰ����
            if (cacheMap.containsKey(key)) {
                moveToTailOfQueue(key);
                cacheMap.put(key, value);
                return value;
            }
            // 2.�Ƿ񳬳����������������Ļ����Ƴ�����ͷ����Ԫ���Լ����Ӧ�Ļ���
            if (cacheMap.size() == maxCapacity) {
                System.out.println("maxCapacity of cache reached");
                removeOldestKey();
            }
            // 3.key�������ڵ�ǰ���档��key��ӵ����е�β�����һ���key�����Ӧ��Ԫ��
            keys.add(key);
            cacheMap.put(key, value);
            if (expireTime > 0) {
                removeAfterExpireTime(key, expireTime);
            }
            return value;
        } finally {
            writeLock.unlock();
        }
    }

    public V get(K key) {
        // �Ӷ���
        readLock.lock();
        try {
            // key�Ƿ�����ڵ�ǰ����
            if (cacheMap.containsKey(key)) {
                // ���ڵĻ��ͽ�key�ƶ������е�β��
                moveToTailOfQueue(key);
                return cacheMap.get(key);
            }
            // �������ڵ�ǰ�����оͷ���Null
            return null;
        } finally {
            readLock.unlock();
        }
    }

    public V remove(K key) {
        writeLock.lock();
        try {
            // key�Ƿ�����ڵ�ǰ����
            if (cacheMap.containsKey(key)) {
                // �����Ƴ����к�Map�ж�Ӧ��Key
                keys.remove(key);
                return cacheMap.remove(key);
            }
            // �������ڵ�ǰ�����оͷ���Null
            return null;
        } finally {
            writeLock.unlock();
        }
    }

    /**
     * ��Ԫ����ӵ����е�β��(put/get��ʱ��ִ��)
     */
    private void moveToTailOfQueue(K key) {
        keys.remove(key);
        keys.add(key);
    }

    /**
     * �Ƴ�����ͷ����Ԫ���Լ����Ӧ�Ļ��� (��������������ʱ��ִ��)
     */
    private void removeOldestKey() {
        K oldestKey = keys.poll();
        if (oldestKey != null) {
            cacheMap.remove(oldestKey);
        }
    }

    private void removeAfterExpireTime(K key, long expireTime) {
        scheduledExecutorService.schedule(() -> {
            // ���ں�����ü�ֵ��
            cacheMap.remove(key);
            keys.remove(key);
        }, expireTime, TimeUnit.MILLISECONDS);
    }

    public int size() {
        return cacheMap.size();
    }
}
