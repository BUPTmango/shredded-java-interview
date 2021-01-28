package singleton;


import annotation.ThreadSafe;

/**
 * ����ʽ
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/1/26 12:07 ����
 */
@ThreadSafe("ͨ����getInstance���������synchronized����֤�̰߳�ȫ")
public class LazyManThreadSafe {
    private static LazyManThreadSafe singleton;

    private LazyManThreadSafe() {
    }

    public static synchronized LazyManThreadSafe getInstance() {
        if (singleton == null) {
            singleton = new LazyManThreadSafe();
        }
        return singleton;
    }
}
