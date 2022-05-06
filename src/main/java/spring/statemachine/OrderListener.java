package spring.statemachine;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;

/**
 * 事件监听器
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2022/5/6 12:47
 */
@WithStateMachine
@Slf4j
@Data
public class OrderListener {

    private String orderStatus = OrderState.CREATED.name();

    /**
     * 注解中传入的是目标状态
     *
     * @param message 可以从message中拿出一些属性
     */
    @OnTransition(target = "PENDING_PAYMENT")
    public void pendingPayment(Message message){
        log.info("订单创建，等待付款, status={} header={}", OrderState.PENDING_PAYMENT.name(),
                message.getHeaders().get("orderId"));

        // TODO 模拟业务流程
        setOrderStatus(OrderState.PENDING_PAYMENT.name());
    }

    @OnTransition(target = "PENDING_DELIVERY")
    public void pendingDelivery() {
        log.info("订单已付款，等待发货, status={} ",
                OrderState.PENDING_DELIVERY.name());
        // TODO 模拟业务流程
        setOrderStatus(OrderState.PENDING_DELIVERY.name());
    }

    @OnTransition(target = "ORDER_COMPLETE")
    public void complete() {
        log.info("订单完成, status={}",
                OrderState.ORDER_COMPLETE.name());
        // TODO 模拟业务流程
        setOrderStatus(OrderState.ORDER_COMPLETE.name());
    }
}
