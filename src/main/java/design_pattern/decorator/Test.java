package design_pattern.decorator;

import org.apache.log4j.Logger;

/**
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/11/28 17:12
 */
public class Test {


    public static void main(String[] args) {
        String str = "{\"code\": 0, \"data\": {\"status\": 1,\"number\": \"215646454\"," +
                "\"account_name\": \"吴系挂\",\"type\":\"中国银行\" ,\"address\": \"某某支行\",\"icon\": \"http://xxx.xxx.xx\"}}";

        Logger logger = Logger.getLogger(Test.class);
        logger.info(str);

        Logger formatLogger = MyLoggerFactory.getLogger(Test.class.getName());
        formatLogger.info(str);
    }
}
