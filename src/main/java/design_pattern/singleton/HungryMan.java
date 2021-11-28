package design_pattern.singleton;


import annotation.ThreadSafe;

/**
 * 饿汉式
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/1/26 12:12 下午
 */
@ThreadSafe("基于classloader机制避免了多线程的同步问题")
public class HungryMan {
    private static HungryMan singleton = new HungryMan();

    private HungryMan() {
    }

    public static HungryMan getInstance() {
        return singleton;
    }
}
