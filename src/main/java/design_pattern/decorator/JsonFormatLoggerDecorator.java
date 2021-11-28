package design_pattern.decorator;

import org.apache.log4j.Logger;

/**
 * 装饰器 使logger输出的json格式化
 * 重要！！
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/11/28 17:32
 */
public class JsonFormatLoggerDecorator extends Logger {

    private static Logger logger;

    public JsonFormatLoggerDecorator(Logger logger) {
        super(JsonFormatLoggerDecorator.class.getName());
        JsonFormatLoggerDecorator.logger = logger;
    }

    @Override
    public void info(Object message) {
        // 在已有功能的基础上添加附加功能
        if (message instanceof String) {
            logger.info(JsonUtil.format((String) message));
        } else {
            logger.info(message);
        }
    }
}
