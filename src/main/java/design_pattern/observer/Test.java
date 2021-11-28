package design_pattern.observer;

/**
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/11/28 21:46
 */
public class Test {
    public static void main(String[] args) {
        ISubject message = new Message();
        message.registerObserver(new ServiceA());
        message.registerObserver(new ServiceB());
        message.notifyObservers();
    }
}
