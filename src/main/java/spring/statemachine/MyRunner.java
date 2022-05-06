package spring.statemachine;

import org.springframework.boot.CommandLineRunner;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;

import javax.annotation.Resource;

/**
 * 模拟发送一些事件的类
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2022/5/6 12:51
 */
public class MyRunner implements CommandLineRunner {

    @Resource
    StateMachine<OrderState, OrderEvent> stateMachine;

    @Override
    public void run(String... args) throws Exception {
        stateMachine.start();

        Message message = MessageBuilder
                .withPayload(OrderEvent.PLACE_ORDER)    // 下单事件
                .setHeader("orderId", "998")    // 消息头里包含订单id
                .build();

        // 发送几个事件
        stateMachine.sendEvent(message);
        stateMachine.sendEvent(OrderEvent.PAID);    // 已付款
        stateMachine.sendEvent(OrderEvent.DELIVERED);   // 已发货
    }
}

