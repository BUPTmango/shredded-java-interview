package proxy.staticproxy;

/**
 * Ŀ�����
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/1/28 4:53 ����
 */
public class UserDao implements IUserDao {

    @Override
    public void save() {
        System.out.println("��������");
    }
}
