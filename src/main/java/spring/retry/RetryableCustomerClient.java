package spring.retry;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * 通过spring retry包装调用的client类
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2022/5/8 14:29
 */
@Service
public class RetryableCustomerClient {

    @Resource
    private CustomerSrvClient customerSrvClient;

    /**
     * 通过@Retryable注解方式
     *
     * value表示抛出何种异常时重试
     * maxAttempts表示最大重试次数
     * delay表示重试的延迟时间
     * maxDelay表示最大延迟时间
     * multiplier指定延迟的倍数
     * random表示延迟时间随机
     *
     * @return
     */
    @Retryable(value = RuntimeException.class, maxAttempts = 3, backoff =
    @Backoff(delay = 500L, maxDelay = 3000L, multiplier = 2, random = true))
    public Optional<Integer> getCustomerAmount1() {
        return customerSrvClient.getCustomerAmount();
    }

    /**
     * 通过RetryTemplate声明方式
     *
     * @return
     */
    public Optional<Integer> getCustomerAmount2() {
        RetryTemplate retryTemplate = RetryTemplate.builder()
                .maxAttempts(3)
                .retryOn(RuntimeException.class)
                .exponentialBackoff(300L, 2, 5000L, true)
                .build();
        return retryTemplate.execute(arg -> customerSrvClient.getCustomerAmount());
    }
}
