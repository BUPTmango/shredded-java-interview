package design_pattern.observer;

/**
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/11/28 21:36
 */
public interface ISubject {
    /**
     * ע��۲���
     *
     * @param observer
     */
    void registerObserver(Observer observer);

    /**
     * ɾ���۲���
     *
     * @param observer
     */
    void removeObserver(Observer observer);

    /**
     * ֪ͨ�۲���
     */
    void notifyObservers();
}
