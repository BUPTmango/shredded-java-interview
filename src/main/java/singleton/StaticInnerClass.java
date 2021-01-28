package singleton;


import annotation.ThreadSafe;

/**
 * 登记式/静态内部类
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/1/26 12:25 下午
 */
@ThreadSafe("利用了classloader 机制来保证初始化并且延迟加载")
public class StaticInnerClass {
    private static class SingletonHolder {
        private static final StaticInnerClass SINGLETON = new StaticInnerClass();
    }

    private StaticInnerClass() {
    }

    public static final StaticInnerClass getInstance() {
        return SingletonHolder.SINGLETON;
    }
}
