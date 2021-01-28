package singleton;


import annotation.NotThreadSafe;

/**
 * ����ʽ
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/1/26 12:07 ����
 */
@NotThreadSafe("û��������")
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
