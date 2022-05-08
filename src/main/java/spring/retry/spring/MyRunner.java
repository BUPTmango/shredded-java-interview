package spring.retry.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;

import javax.annotation.Resource;

/**
 * @author Wang Guolong
 * @version 1.0
 * @date 2022/5/8 14:39
 */@Slf4j
public class MyRunner implements CommandLineRunner {

    @Resource
    private RetryableCustomerClient retryableCustomerClient;

    @Override
    public void run(String... args) throws Exception {
        try {
            retryableCustomerClient.getCustomerAmount1();
        } catch (Exception e) {
            log.info("retryableCustomerClient.getCustomerAmount1()异常");
        }
        try {
            retryableCustomerClient.getCustomerAmount2();
        } catch (Exception e) {
            log.info("retryableCustomerClient.getCustomerAmount2()异常");
        }
    }
}
