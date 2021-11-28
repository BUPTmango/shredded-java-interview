package design_pattern.strategy;

import java.util.Collections;
import java.util.List;

/**
 * Ĭ���������
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/11/28 16:40
 */
public class DefaultSortStrategy implements SortStrategy{
    @Override
    public List<String> sort(List<String> icons) {
        System.out.println("ִ��Ĭ���������");
        Collections.sort(icons);
        return icons;
    }
}
