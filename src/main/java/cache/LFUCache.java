package cache;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * LFU缓存实现
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/2/2 2:41 下午
 */
public class LFUCache {
    /**
     * key到val的映射
     */
    private Map<Integer, Integer> keyToVal;
    /**
     * key到freq的映射
     */
    private Map<Integer, Integer> keyToFreq;
    /**
     * freq到key列表的映射
     */
    private Map<Integer, LinkedHashSet<Integer>> freqToKeys;
    /**
     * 记录最小的频次
     */
    private int minFreq;
    /**
     * 记录最大容量
     */
    private int cap;

    public LFUCache(int capacity) {
        cap = capacity;
        keyToVal = new HashMap<>();
        keyToFreq = new HashMap<>();
        freqToKeys = new HashMap<>();
        minFreq = 0;
    }

    public int get(int key) {
        if (!keyToVal.containsKey(key)) {
            return -1;
        }
        // 增加key对应的freq
        increaseFreq(key);
        return keyToVal.get(key);
    }

    public void put(int key, int value) {
        if (cap <= 0) {
            return;
        }
        // 如果key已经存在 修改对应的val即可
        if (keyToVal.containsKey(key)) {
            keyToVal.put(key, value);
            increaseFreq(key);
        } else {
            // 如果容量已经满了 淘汰一个key
            if (cap <= keyToVal.size()) {
                removeMinFreqKey();
            }
            // 插入KV表
            keyToVal.put(key, value);
            // 插入KF表
            keyToFreq.put(key, 1);
            // 插入FK表
            freqToKeys.putIfAbsent(1, new LinkedHashSet<>());
            freqToKeys.get(1).add(key);
            // 插入新key后 最小的freq肯定是1
            minFreq = 1;
        }
    }

    private void removeMinFreqKey() {
        LinkedHashSet<Integer> keyList = freqToKeys.get(minFreq);
        int deletedKey = keyList.iterator().next();
        // 更新FK表
        keyList.remove(deletedKey);
        if (keyList.isEmpty()) {
            freqToKeys.remove(minFreq);
            // 这里不需要再更新minFreq 因为remove之后put会将minFreq更新为1
        }
        // 更新KV表
        keyToVal.remove(deletedKey);
        // 更新KF表
        keyToFreq.remove(deletedKey);
    }

    private void increaseFreq(int key) {
        int freq = keyToFreq.get(key);
        // 更新KF表
        keyToFreq.put(key, freq + 1);
        // 更新FK表
        // 先将key从freq的列表中删除
        freqToKeys.get(freq).remove(key);
        // 再将key加入到freq+1的列表中
        freqToKeys.putIfAbsent(freq + 1, new LinkedHashSet<>());
        freqToKeys.get(freq + 1).add(key);
        // 如果freq对应的列表空了 移除了这个freq
        if (freqToKeys.get(freq).isEmpty()) {
            freqToKeys.remove(freq);
            // 如果这个freq恰好是minFreq 更新minFreq
            if (freq == minFreq) {
                minFreq++;
            }
        }
    }
}
