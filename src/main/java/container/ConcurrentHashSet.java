package container;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 自己实现一个ConcurrentHashSet
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/3/30 15:05
 */
public class ConcurrentHashSet<T> extends AbstractSet<T> {
    private static final Object PRESENT = new Object();
    private final ConcurrentHashMap<T, Object> map;

    public ConcurrentHashSet() {
        map = new ConcurrentHashMap();
    }

    public ConcurrentHashSet(int initialCapacity) {
        this.map = new ConcurrentHashMap<>(initialCapacity);
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return map.containsKey(o);
    }

    @Override
    public boolean add(T value) {
        return this.map.put(value, PRESENT) == null;
    }

    @Override
    public boolean remove(Object value) {
        return this.map.remove(value) != null;
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Iterator<T> iterator() {
        return this.map.keySet().iterator();
    }

    @Override
    public int size() {
        return this.map.size();
    }
}
