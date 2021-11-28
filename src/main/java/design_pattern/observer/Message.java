package design_pattern.observer;

import java.util.Vector;

/**
 * 被观察者 消息类
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/11/28 21:38
 */
public class Message implements ISubject {

    private Vector<Observer> obs = new Vector<>();

    @Override
    public void registerObserver(Observer observer) {
        obs.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        obs.remove(observer);
    }

    @Override
    public void notifyObservers() {
        Object[] arrLocal;
        synchronized (this) {
            arrLocal = obs.toArray();
        }
        for (Object ob : arrLocal) {
            ((Observer) ob).update();
        }
    }
}
