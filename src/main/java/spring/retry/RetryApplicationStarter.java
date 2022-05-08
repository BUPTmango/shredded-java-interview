package spring.retry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.annotation.EnableRetry;

/**
 * @author Wang Guolong
 * @version 1.0
 * @date 2022/5/8 14:41
 */
@SpringBootApplication
@EnableRetry
public class RetryApplicationStarter {

    @Bean
    public MyRunner chickenRun() {
        return new MyRunner();
    }

    public static void main(String[] arg) {
        SpringApplication.run(RetryApplicationStarter.class, arg);
    }

}
