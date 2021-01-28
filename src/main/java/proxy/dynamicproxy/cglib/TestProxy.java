package proxy.dynamicproxy.cglib;

import org.junit.Test;

/**
 * 测试类
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/1/28 5:23 下午
 */
public class TestProxy {

    @Test
    public void testCglibProxy() {
        // 目标对象
        UserDao target = new UserDao();
        System.out.println(target.getClass());
        // 代理对象
        UserDao proxy = (UserDao) new ProxyFactory(target).getProxyInstance();
        System.out.println(proxy.getClass());
        // 执行代理对象方法
        proxy.save();
    }
}
