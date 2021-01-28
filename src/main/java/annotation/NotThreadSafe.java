package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义表示线程不安全的注解
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/1/26 12:19 下午
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface NotThreadSafe {
    String value();
}
