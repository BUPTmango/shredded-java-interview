package design_pattern.singleton;


import annotation.ThreadSafe;

/**
 * ˫����/˫��У������DCL���� double-checked locking��
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/1/25 4:47 ����
 */
@ThreadSafe("ͨ��˫�����ƣ��ڶ��߳�����±��ָ�����")
public class DoubleCheckedLocking {

    // 1:volatile����
    private volatile static DoubleCheckedLocking singleton;

    private DoubleCheckedLocking() {
    }

    public static DoubleCheckedLocking getSingleton() {
        // 2:���ٲ�Ҫͬ�����Ż�����
        if (singleton == null) {
            // 3��ͬ�����̰߳�ȫ
            synchronized (DoubleCheckedLocking.class) {
                if (singleton == null) {
                    // 4������singleton ����
                    singleton = new DoubleCheckedLocking();
                }
            }
        }
        return singleton;
    }
}
