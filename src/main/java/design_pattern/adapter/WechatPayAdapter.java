package design_pattern.adapter;

/**
 * wechat  ≈‰∆˜
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/11/28 21:57
 */
public class WechatPayAdapter implements Pay{

    private WechatPay wechatPay;

    public WechatPayAdapter(WechatPay wechatPay) {
        this.wechatPay = wechatPay;
    }

    @Override
    public void pay() {
        wechatPay.payment();
    }
}
