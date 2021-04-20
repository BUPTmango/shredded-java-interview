package datastructure;

/**
 * Java实现BitMap
 * 参考：https://sustcoder.github.io/2018/10/23/2018-10-23-bitMap-explain-and-use/
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/4/20 17:50
 */
public class BitMap {
    private long length;
    private static int[] bitsMap;
    private static final int[] BIT_VALUE = {0x00000001, 0x00000002, 0x00000004, 0x00000008, 0x00000010, 0x00000020,
            0x00000040, 0x00000080, 0x00000100, 0x00000200, 0x00000400, 0x00000800, 0x00001000, 0x00002000, 0x00004000,
            0x00008000, 0x00010000, 0x00020000, 0x00040000, 0x00080000, 0x00100000, 0x00200000, 0x00400000, 0x00800000,
            0x01000000, 0x02000000, 0x04000000, 0x08000000, 0x10000000, 0x20000000, 0x40000000, 0x80000000};

    public BitMap(long length) {
        this.length = length;
        /**
         * 根据长度算出，所需数组大小
         * 当 length%32=0 时大小等于
         * = length/32
         * 当 length%32>0 时大小等于
         * = length/32+l
         */
        bitsMap = new int[(int) (length >> 5) + ((length & 31) > 0 ? 1 : 0)];
    }

    /**
     * @param n 要被设置的值为n
     */
    public void setN(long n) {
        if (n < 0 || n > length) {
            throw new IllegalArgumentException("length value " + n + " is  illegal!");
        }
        // 求出该n所在bitMap的下标,等价于"n/5"
        int index = (int) n >> 5;
        // 求出该值的偏移量(求余),等价于"n%31"
        int offset = (int) n & 31;
        /**
         * 等价于
         * int bits = bitsMap[index];
         * bitsMap[index]=bits| BIT_VALUE[offset];
         * 例如,n=3时,设置byte第4个位置为1 （从0开始计数，bitsMap[0]可代表的数为：0~31，从左到右每一个bit位表示一位数）
         * bitsMap[0]=00000000 00000000 00000000 00000000  |  00000000 00000000 00000000 00001000=00000000 00000000 00000000 00000000 00001000
         * 即: bitsMap[0]= 0 | 0x00000008 = 3
         *
         * 例如,n=4时,设置byte第5个位置为1
         * bitsMap[0]=00000000 00000000 00000000 00001000  |  00000000 00000000 00000000 00010000=00000000 00000000 00000000 00000000 00011000
         * 即: bitsMap[0]=3 | 0x00000010 = 12
         */
        bitsMap[index] |= BIT_VALUE[offset];

    }

    /**
     * 获取值N是否存在
     *
     * @return 1：存在，0：不存在
     */
    public int isExist(long n) {
        if (n < 0 || n > length) {
            throw new IllegalArgumentException("length value illegal!");
        }
        int index = (int) n >> 5;
        int offset = (int) n & 31;
        int bits = bitsMap[index];
        // System.out.println("n="+n+",index="+index+",offset="+offset+",bits="+Integer.toBinaryString(bitsMap[index]));
        return ((bits & BIT_VALUE[offset])) >>> offset;
    }
}
