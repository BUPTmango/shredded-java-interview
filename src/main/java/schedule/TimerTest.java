package schedule;

import lombok.extern.slf4j.Slf4j;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 使用Timer和TimerTask手写调度器
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/5/21 11:30
 */
@Slf4j
public class TimerTest {
    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                log.info("1s gone");
            }
        }, 5000, 1000);
    }
}
