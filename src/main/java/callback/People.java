package callback;

/**
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/4/20 18:53
 */
public class People {

    Printer printer = new Printer();

    /**
     * 同步回调
     */
    public void goToPrintSyn(Callback callback, String text) {
        printer.print(callback, text);
    }

    /**
     * 异步回调
     */
    public void goToPrintASyn(Callback callback, String text) {
        // 区别就是异步新开了一个线程
        new Thread(new Runnable() {
            public void run() {
                printer.print(callback, text);
            }
        }).start();
    }
}
