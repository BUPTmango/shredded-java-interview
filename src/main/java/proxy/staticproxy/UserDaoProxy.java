package proxy.staticproxy;

/**
 * ��̬�������
 * ��Ҫʵ��IUserDao�ӿ�
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/1/28 4:57 ����
 */
public class UserDaoProxy implements IUserDao {

    private IUserDao target;

    public UserDaoProxy(IUserDao target) {
        this.target = target;
    }

    @Override
    public void save() {
        // ��չ�˶��⹦��
        System.out.println("��������");
        target.save();
        System.out.println("�ύ����");
    }
}
