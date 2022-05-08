package spring.retry;

import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 模拟rpc接口
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2022/5/8 14:27
 */
@Service
public class CustomerSrvClient {
    public Optional<Integer> getCustomerAmount() {
        System.out.println("called");
        throw new RuntimeException("远程接口错误");
    }
}
