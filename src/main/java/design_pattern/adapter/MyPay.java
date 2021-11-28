package design_pattern.adapter;

/**
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/11/28 21:59
 */
public class MyPay implements Pay{
    @Override
    public void pay() {
        System.out.println("使用内部支付");
    }
}
