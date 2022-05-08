package spring.retry.guava;

import com.github.rholder.retry.*;
import com.google.common.base.Predicates;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Guava Retry
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2022/5/8 17:35
 */
public class RetryTest {

    public static void main(String[] args) {
        Callable<Boolean> callable = new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return true;  // do something useful here
            }
        };

        Retryer<Boolean> retryer = RetryerBuilder.<Boolean>newBuilder()
                .retryIfResult(Predicates.<Boolean>isNull())  // callable返回null时重试
                .retryIfExceptionOfType(IOException.class)  // callable抛出IOException重试
                .retryIfRuntimeException()  // callable抛出RuntimeException重试
                .withWaitStrategy(WaitStrategies.exponentialWait(100, 5, TimeUnit.MINUTES))  // 指数退避
//                .withWaitStrategy(WaitStrategies.fibonacciWait(100, 2, TimeUnit.MINUTES))  // 斐波那契退避
                .withStopStrategy(StopStrategies.stopAfterAttempt(3))  // 重试3次后停止
//                .withStopStrategy(StopStrategies.neverStop())  // 永远不停止重试
                .withRetryListener(new DiyRetryListener<Boolean>())  // 注册监听器
                .build();

        try {
            retryer.call(callable);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (RetryException e) {
            e.printStackTrace();
        }
    }


}
