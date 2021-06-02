package jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * ���� JVM fast throw �ײ��Ż�
 *
 * ���Թ۲���֮ǰ�ж�ջ����Ϊ3��֮���Ϊ0
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/6/2 21:52
 */
public class FastThrow {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            try {
                // ĳһ���쳣��ͬһ���ط�������׳���JVM���׳�һ��Ԥ�����쳣�����̵� message �� stack trace
                list.get(-1);
            } catch (Exception e) {
                int length = e.getStackTrace().length;
                System.out.println(String.format("�����쳣 :: %s, ��ջ���� :: %s", e, length));
            }
        }
    }
}
