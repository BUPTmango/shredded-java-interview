package spring.statemachine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * 1 定义状态和事件的枚举类
 * 2 定义状态机配置（设置初始状态以及状态与事件之间的关系）
 * 3 定义状态监听器 （当状态变更时，触发方法）
 *
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