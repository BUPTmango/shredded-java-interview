package design_pattern.observer;

/**
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/11/28 21:36
 */
public interface ISubject {
    /**
     * 注册观察者
     *
     * @param observer
     */
    void registerObserver(Observer observer);

    /**
     * 删除观察者
     *
     * @param observer
     */
    void removeObserver(Observer observer);

    /**
     * 通知观察者
     */
    void notifyObservers();
}
