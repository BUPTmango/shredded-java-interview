package design_pattern.singleton;


import annotation.ThreadSafe;

/**
 * 双检锁/双重校验锁（DCL，即 double-checked locking）
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/1/25 4:47 下午
 */
@ThreadSafe("通过双锁机制，在多线程情况下保持高性能")
public class DoubleCheckedLocking {

    // 1:volatile修饰
    private volatile static DoubleCheckedLocking singleton;

    private DoubleCheckedLocking() {
    }

    public static DoubleCheckedLocking getSingleton() {
        // 2:减少不要同步，优化性能
        if (singleton == null) {
            // 3：同步，线程安全
            synchronized (DoubleCheckedLocking.class) {
                if (singleton == null) {
                    // 4：创建singleton 对象
                    singleton = new DoubleCheckedLocking();
                }
            }
        }
        return singleton;
    }
}
