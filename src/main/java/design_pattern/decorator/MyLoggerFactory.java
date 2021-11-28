package design_pattern.decorator;

import org.apache.log4j.Logger;

/**
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/11/28 21:19
 */
public class MyLoggerFactory {

    public static Logger getLogger(String name) {
        // 此处可以进行多层装饰，给日志增加很多功能
        return new JsonFormatLoggerDecorator(Logger.getLogger(name));
    }
}
