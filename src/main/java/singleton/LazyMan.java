package singleton;


import annotation.NotThreadSafe;

/**
 * 懒汉式
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/1/26 12:07 下午
 */
@NotThreadSafe("没有锁机制")
public class LazyMan {
    private static LazyMan singleton;

    private LazyMan() {
    }

    public static LazyMan getInstance() {
        if (singleton == null) {
            singleton = new LazyMan();
        }
        return singleton;
    }
}
