package design_pattern.adapter;

/**
 * ʹ��ͳһ�Ľӿڵ����Լ��ķ�������ǵ���������
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
