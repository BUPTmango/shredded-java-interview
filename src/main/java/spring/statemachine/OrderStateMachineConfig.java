package spring.statemachine;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

/**
 * 配置类
 * 配置初始状态和事件转化关系
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2022/5/6 12:51
 */
@Configuration
@EnableStateMachine
public class OrderStateMachineConfig extends EnumStateMachineConfigurerAdapter<OrderState, OrderEvent> {


    @Override
    public void configure(StateMachineStateConfigurer<OrderState, OrderEvent> states) throws Exception {
        states.withStates()
                .initial(OrderState.CREATED)    // 指定初始化状态
                .states(EnumSet.allOf(OrderState.class));
    }

    /**
     * 配置状态机如何做流转
     *
     * @param transitions
     * @throws Exception
     */
    @Override
    public void configure(StateMachineTransitionConfigurer<OrderState, OrderEvent> transitions) throws Exception {
        transitions
                .withExternal()     // 配置两个不同状态之间的流转
                .source(OrderState.CREATED)     // 创建订单
                .target(OrderState.PENDING_PAYMENT)     // 待付款
                .event(OrderEvent.PLACE_ORDER)  // 下单
                .and().withExternal()
                .source(OrderState.PENDING_PAYMENT)
                .target(OrderState.PENDING_DELIVERY)
                .event(OrderEvent.PAID)
                .and().withExternal()
                .source(OrderState.PENDING_DELIVERY)
                .target(OrderState.ORDER_COMPLETE)
                .event(OrderEvent.DELIVERED);
    }
}
