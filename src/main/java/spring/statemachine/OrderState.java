package spring.statemachine;

/**
 * 订单状态枚举类
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2022/5/6 12:44
 */
public enum OrderState {
    /*
    创建订单
     */
    CREATED,

    /*
    等待付款
     */
    PENDING_PAYMENT,

    /*
    等待配送
     */
    PENDING_DELIVERY,

    /*
    订单完结
     */
    ORDER_COMPLETE
}
