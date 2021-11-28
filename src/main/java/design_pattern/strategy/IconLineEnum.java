package design_pattern.strategy;

/**
 * icon区样式枚举
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/11/28 16:47
 */
public enum IconLineEnum {
    /**
     * 单行icon
     */
    SINGLE_LINE(1),
    /**
     * 单行icon
     */
    DOUBLE_LINE(2);

    private int type;

    IconLineEnum(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
