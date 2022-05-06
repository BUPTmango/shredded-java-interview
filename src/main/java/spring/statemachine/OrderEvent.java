package spring.statemachine;

/**
 * 订单事件枚举类
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2022/5/6 12:45
 */
public enum OrderEvent {
    /*
    下单
     */
    PLACE_ORDER,

    /*
    付款完成
     */
    PAID,

    /*
    配送成功
     */
    DELIVERED
}

