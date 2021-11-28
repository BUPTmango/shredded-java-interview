package design_pattern.strategy;

import java.util.ArrayList;

/**
 * 模拟淘宝首页上方icon区
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/11/28 16:38
 */
public class IconArea {

    private SortStrategy strategy;

    public void showIcons(int type) {
        setStrategy(type);
        strategy.sort(new ArrayList<>());
    }

    private void setStrategy(int type) {
        if (type == IconLineEnum.SINGLE_LINE.getType()) {
            strategy = new SingleLineIconSortStrategy();
        } else if (type == IconLineEnum.DOUBLE_LINE.getType()) {
            strategy = new DoubleLineIconSortStrategy();
        } else {
            strategy = new DefaultSortStrategy();
        }
    }
}
