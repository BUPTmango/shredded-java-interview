package algorithm.lru;

import java.util.LinkedHashMap;

/**
 * 实现LRU
 * 通过LinkedHashMap简单实现
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/9/22 22:09
 */
public class SimpleLRU {
    private int cap;
    private LinkedHashMap<Integer, Integer> cache;

    public SimpleLRU(int capacity) {
        cap = capacity;
        cache = new LinkedHashMap<>();
    }

    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        makeRecently(key);
        return cache.get(key);
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            cache.put(key, value);
            makeRecently(key);
        } else {
            if (cap == cache.size()) {
                int oldestKey = cache.keySet().iterator().next();
                cache.remove(oldestKey);
            }
            cache.put(key, value);
        }
    }

    private void makeRecently(int key) {
        int val = cache.get(key);
        // 删除并重新放到队尾
        cache.remove(key);
        cache.put(key, val);
    }
}
