package design_pattern.decorator;

import org.apache.log4j.Logger;

/**
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/11/28 21:19
 */
public class MyLoggerFactory {

    public static Logger getLogger(String name) {
        // �˴����Խ��ж��װ�Σ�����־���Ӻܶ๦��
        return new JsonFormatLoggerDecorator(Logger.getLogger(name));
    }
}
