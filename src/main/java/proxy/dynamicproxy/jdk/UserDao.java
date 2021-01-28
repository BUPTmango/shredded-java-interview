package proxy.dynamicproxy.jdk;

/**
 * 目标对象
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/1/28 5:14 下午
 */
public class UserDao implements IUserDao {

    @Override
    public void save() {
        System.out.println("保存数据");
    }
}
