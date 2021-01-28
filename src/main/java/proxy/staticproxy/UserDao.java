package proxy.staticproxy;

/**
 * 目标对象
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/1/28 4:53 下午
 */
public class UserDao implements IUserDao {

    @Override
    public void save() {
        System.out.println("保存数据");
    }
}
