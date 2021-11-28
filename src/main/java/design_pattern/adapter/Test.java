package design_pattern.adapter;

/**
 * 使用统一的接口调用自己的服务或者是第三方服务
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/11/28 21:58
 */
public class Test {
    public static void main(String[] args) {
        Pay myPay = new MyPay();
        myPay.pay();
        Pay ali = new AlipayAdapter(new Alipay());
        ali.pay();
        Pay wechat = new WechatPayAdapter(new WechatPay());
        wechat.pay();
    }
}
