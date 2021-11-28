package design_pattern.adapter;

/**
 * aliÊÊÅäÆ÷
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/11/28 21:53
 */
public class AlipayAdapter implements Pay {

    private Alipay alipay;

    public AlipayAdapter(Alipay alipay) {
        this.alipay = alipay;
    }

    @Override
    public void pay() {
        alipay.payment();
    }
}
