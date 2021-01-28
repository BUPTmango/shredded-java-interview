package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * �Զ����ʾ�̲߳���ȫ��ע��
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/1/26 12:19 ����
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface NotThreadSafe {
    String value();
}
