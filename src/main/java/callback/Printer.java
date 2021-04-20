package callback;

/**
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/4/20 18:53
 */
public class Printer {
    public void print(Callback callback, String text) {
        System.out.println("正在打印 . . . ");
        try {
            Thread.currentThread();
            Thread.sleep(3000);// 毫秒
        } catch (Exception e) {
        }
        callback.printFinished("打印完成");
    }
}
