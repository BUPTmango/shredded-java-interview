package design_pattern.adapter;

/**
 * 统一支付接口
 * 实现类中有自己的支付也有第三方的支付
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/11/28 21:51
 */
public interface Pay {
    /**
     * 支付方法
     */
    void pay();
}
