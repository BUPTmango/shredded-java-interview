package proxy.staticproxy;

import org.junit.Test;

/**
 * 测试类
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/1/28 4:59 下午
 */
public class TestProxy {
    @Test
    public void testStaticProxy() {
        //目标对象
        IUserDao target = new UserDao();
        //代理对象
        UserDaoProxy proxy = new UserDaoProxy(target);
        proxy.save();
    }
}
