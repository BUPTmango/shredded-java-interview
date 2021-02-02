package cache;

import java.util.HashMap;

/**
 * LRU缓存实现
 * 注意这里要手写双向链表
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/2/1 9:48 下午
 */
public class LRUCache {
    private HashMap<Integer, Node> map;

    private Node head, tail;

    private int size, capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        map = new HashMap<>();
        head = new Node();
        tail = new Node();
        // 头尾相连
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        // 不存在返回-1
        if (!map.containsKey(key)) {
            return -1;
        }
        // 获取value并放到头部
        Node node = map.get(key);
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = map.get(key);
        // 如果已经存在当前的key直接替换value 并放到头部
        if (node != null) {
            node.value = value;
            moveToHead(node);
        } else {
            // 不存在就插入
            // 如果已经满了就删除队尾的元素
            if (size == capacity) {
                Node removeNode = removeTail();
                map.remove(removeNode.key);
                size--;
            }
            // 插入到头部
            Node newNode = new Node(key, value);
            addToHead(newNode);
            map.put(key, newNode);
            size++;
        }
    }

    private void addToHead(Node node) {
        node.next = head.next;
        head.next = node;
        node.next.prev = node;
        node.prev = head;
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void moveToHead(Node node) {
        removeNode(node);
        addToHead(node);
    }

    private Node removeTail() {
        Node node = tail.prev;
        removeNode(node);
        return node;
    }


    private class Node {
        private int key, value;

        private Node prev, next;

        public Node() {
        }

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}
