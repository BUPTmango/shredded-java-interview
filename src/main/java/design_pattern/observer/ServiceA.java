package design_pattern.observer;

/**
 * 观察者 服务A
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/11/28 21:35
 */
public class ServiceA extends Observer{
    @Override
    public void update() {
        System.out.println("服务A收到消息，执行初始化逻辑");
    }
}
