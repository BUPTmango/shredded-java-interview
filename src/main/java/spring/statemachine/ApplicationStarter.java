package spring.statemachine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author Wang Guolong
 * @version 1.0
 * @date 2022/5/6 12:59
 */
@SpringBootApplication
public class ApplicationStarter {

    @Bean
    public MyRunner chickenRun() {
        return new MyRunner();
    }

    public static void main(String[] arg) {
        SpringApplication.run(ApplicationStarter.class, arg);
    }

}