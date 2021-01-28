package proxy.dynamicproxy.cglib;

/**
 * 目标对象
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/1/28 5:20 下午
 */
public class UserDao {

    public void save() {
        System.out.println("保存数据");
    }
}
