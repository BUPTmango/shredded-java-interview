package collection;

/**
 * 实现简单的HashMap
 * 只有简单的put和get操作
 * 没有扩容以及树相关的操作（只是数组+链表）
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/9/1 14:51
 */
public class MyHashMap<K, V> {
    static class Node<K, V> {
        final int hash;
        final K key;
        V value;
        Node<K, V> next;
        Node(int hash, K k, V v, Node<K,V> next) {
            this.hash = hash;
            this.key = k;
            this.value = v;
            this.next = next;
        }
    }

    int capacity;
    Node<K, V>[] bucket;
    public MyHashMap(int capacity) {
        this.capacity = capacity;
        bucket = new Node[capacity];
    }

    public void put(K k, V v) {
        int hash = hash(k);
        int index = (bucket.length - 1) & hash;
        if (bucket[index] == null) {
            bucket[index] = new Node<>(hash, k, v, null);
        } else {
            Node<K, V> cur = bucket[index];
            while (cur != null && cur.key != k) {
                if (cur.next == null) {
                    cur.next = new Node<>(hash, k, v, null);
                    break;
                }
                cur = cur.next;
            }
            if (cur.key == k) {
                cur.value = v;
            }
        }
    }

    public V get(K k) {
        int index = hash(k) % capacity;
        Node<K, V> cur = bucket[index];
        while (cur != null && cur.key != k){
            cur = cur.next;
        }
        return cur == null ? null : cur.value;
    }

    private int hash(K k) {
        int h;
        return (k == null) ? 0 : (h = k.hashCode()) ^ (h >>> 16);
    }

    public static void main(String[] args) {
        MyHashMap<Integer, Integer> map = new MyHashMap<>(1);
        map.put(1, 1);
        map.put(1, 10);
        map.put(2, 20);
        System.out.println(map.get(1));
        System.out.println(map.get(2));
    }
}

