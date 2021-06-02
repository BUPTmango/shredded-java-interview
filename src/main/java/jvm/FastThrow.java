package jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试 JVM fast throw 底层优化
 *
 * 可以观察结果之前有堆栈长度为3，之后变为0
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/6/2 21:52
 */
public class FastThrow {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            try {
                // 某一个异常在同一个地方被多次抛出，JVM会抛出一个预分配异常，并吞掉 message 和 stack trace
                list.get(-1);
            } catch (Exception e) {
                int length = e.getStackTrace().length;
                System.out.println(String.format("报错异常 :: %s, 堆栈长度 :: %s", e, length));
            }
        }
    }
}
