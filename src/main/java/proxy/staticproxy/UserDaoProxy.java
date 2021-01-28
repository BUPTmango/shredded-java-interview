package proxy.staticproxy;

/**
 * 静态代理对象
 * 需要实现IUserDao接口
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/1/28 4:57 下午
 */
public class UserDaoProxy implements IUserDao {

    private IUserDao target;

    public UserDaoProxy(IUserDao target) {
        this.target = target;
    }

    @Override
    public void save() {
        // 扩展了额外功能
        System.out.println("开启事务");
        target.save();
        System.out.println("提交事务");
    }
}
