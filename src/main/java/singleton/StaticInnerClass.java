package singleton;


import annotation.ThreadSafe;

/**
 * �Ǽ�ʽ/��̬�ڲ���
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/1/26 12:25 ����
 */
@ThreadSafe("������classloader ��������֤��ʼ�������ӳټ���")
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
