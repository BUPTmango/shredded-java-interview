package datastructure;

import java.util.BitSet;

/**
 * Java实现布隆过滤器
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/4/21 11:17
 */
public class BloomFilter {

    /**
     * 使用加法hash算法，所以定义了一个8个元素的质数数组
     */
    private static final int[] primes = new int[]{2, 3, 5, 7, 11, 13, 17, 19};
    /**
     * 用八个不同的质数，相当于构建8个不同算法
     */
    private Hash[] hashList = new Hash[primes.length];
    /**
     * 创建一个长度为10亿的比特位
     */
    private BitSet bits = new BitSet(256 << 22);

    public BloomFilter() {
        for (int i = 0; i < primes.length; i++) {
            // 使用8个质数，创建八种算法
            hashList[i] = new Hash(primes[i]);
        }
    }

    /**
     * 添加元素
     *
     * @param value
     */
    public void add(String value) {
        for (Hash f : hashList) {
            //算出8个信息指纹，对应到2的32次方个比特位上
            bits.set(f.hash(value), true);
        }
    }

    /**
     * 判断是否在布隆过滤器中
     *
     * @param value
     * @return
     */
    public boolean contains(String value) {
        if (value == null) {
            return false;
        }
        boolean ret = true;
        for (Hash f : hashList) {
            //查看8个比特位上的值
            ret = ret && bits.get(f.hash(value));
        }
        return ret;
    }

    /**
     * 加法hash算法
     */
    public static class Hash {

        private int prime;

        public Hash(int prime) {
            this.prime = prime;
        }

        public int hash(String key) {
            int hash, i;
            for (hash = key.length(), i = 0; i < key.length(); i++) {
                hash += key.charAt(i);
            }
            return (hash % prime);
        }
    }

    public static void main(String[] args) {

        BloomFilter bloomFileter = new BloomFilter();
        System.out.println(bloomFileter.contains("5324512515"));
        bloomFileter.add("5324512515");

        // 维护1亿个在线用户
        for (int i = 1; i < 100000000; i++) {
            bloomFileter.add(String.valueOf(i));
        }

        long begin = System.currentTimeMillis();
        System.out.println(begin);
        System.out.println(bloomFileter.contains("5324512515"));
        long end = System.currentTimeMillis();
        System.out.println(end);
        System.out.println("判断5324512515是否在线使用了:" + (begin - end));
    }
}

