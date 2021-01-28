package singleton;


import annotation.ThreadSafe;

/**
 * 懒汉式
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/1/26 12:07 下午
 */
@ThreadSafe("通过在getInstance方法上添加synchronized来保证线程安全")
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
