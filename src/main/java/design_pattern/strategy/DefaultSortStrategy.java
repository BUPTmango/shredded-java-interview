package design_pattern.strategy;

import java.util.Collections;
import java.util.List;

/**
 * 默认排序策略
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/11/28 16:40
 */
public class DefaultSortStrategy implements SortStrategy{
    @Override
    public List<String> sort(List<String> icons) {
        System.out.println("执行默认排序策略");
        Collections.sort(icons);
        return icons;
    }
}
