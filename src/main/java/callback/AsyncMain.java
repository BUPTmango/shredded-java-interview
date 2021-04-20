package callback;

/**
 * 异步回调
 * 代码执行到需要回调的代码的时候，并不会停下来，而是继续执行，当然可能过一会回调的结果会返回回来。
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/4/20 18:55
 */
public class AsyncMain {
    public static void main(String[] args) {
        People people = new People();
        Callback callback = new Callback() {
            @Override
            public void printFinished(String msg) {
                System.out.println("打印机告诉我的消息是 ---> " + msg);
            }
        };
        System.out.println("需要打印的内容是 ---> " + "打印一份简历");
        people.goToPrintASyn(callback, "打印一份简历");
        System.out.println("我在等待 打印机 给我反馈");
    }
}
