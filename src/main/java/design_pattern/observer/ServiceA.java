package design_pattern.observer;

/**
 * �۲��� ����A
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/11/28 21:35
 */
public class ServiceA extends Observer{
    @Override
    public void update() {
        System.out.println("����A�յ���Ϣ��ִ�г�ʼ���߼�");
    }
}
