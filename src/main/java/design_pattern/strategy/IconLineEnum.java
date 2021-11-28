package design_pattern.strategy;

/**
 * icon����ʽö��
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/11/28 16:47
 */
public enum IconLineEnum {
    /**
     * ����icon
     */
    SINGLE_LINE(1),
    /**
     * ����icon
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
