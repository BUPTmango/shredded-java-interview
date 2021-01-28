package singleton;


import annotation.ThreadSafe;

/**
 * 枚举
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/1/26 12:30 下午
 */
@ThreadSafe("通过枚举类的特性满足线程安全")
public enum Enum {
    INSTANCE;

    public void whateverMethod() {
    }
}
