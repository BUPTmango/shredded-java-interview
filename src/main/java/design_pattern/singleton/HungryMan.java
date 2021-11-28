package design_pattern.singleton;


import annotation.ThreadSafe;

/**
 * ����ʽ
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/1/26 12:12 ����
 */
@ThreadSafe("����classloader���Ʊ����˶��̵߳�ͬ������")
public class HungryMan {
    private static HungryMan singleton = new HungryMan();

    private HungryMan() {
    }

    public static HungryMan getInstance() {
        return singleton;
    }
}
