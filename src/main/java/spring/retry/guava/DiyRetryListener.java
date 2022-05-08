package spring.retry.guava;

import com.github.rholder.retry.Attempt;
import com.github.rholder.retry.RetryListener;
import lombok.extern.slf4j.Slf4j;

/**
 * 重试监听器
 * 当重试发生时，需要额外一些动作
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2022/5/8 17:47
 */
@Slf4j
public class DiyRetryListener<Boolean> implements RetryListener {

    @Override
    public <Boolean> void onRetry(Attempt<Boolean> attempt) {
        log.info("重试次数 :{}", attempt.getAttemptNumber());
        log.info("距离第一次重试的延迟 :{}", attempt.getDelaySinceFirstAttempt());
        if (attempt.hasException()) {
            log.error("异常原因 : ", attempt.getExceptionCause());
        } else {
            log.info("正常处理结果 : {}", attempt.getResult());
        }
    }
}
