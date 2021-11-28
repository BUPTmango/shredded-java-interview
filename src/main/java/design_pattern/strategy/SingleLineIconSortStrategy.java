package design_pattern.strategy;

import java.util.List;

/**
 * 单行icon排序逻辑
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/11/28 16:44
 */
public class SingleLineIconSortStrategy implements SortStrategy{
    @Override
    public List<String> sort(List<String> icons) {
        System.out.println("执行单行icon排序逻辑");
        return null;
    }
}
