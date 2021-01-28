package proxy.dynamicproxy.jdk;

import org.junit.Test;

/**
 * 测试类
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/1/28 5:17 下午
 */
public class TestProxy {

    @Test
    public void testDynamicProxy() {
        IUserDao target = new UserDao();
        // 输出目标对象信息
        System.out.println(target.getClass());
        IUserDao proxy = (IUserDao) new ProxyFactory(target).getProxyInstance();
        // 输出代理对象信息
        System.out.println(proxy.getClass());
        proxy.save();  //执行代理方法
    }
}
