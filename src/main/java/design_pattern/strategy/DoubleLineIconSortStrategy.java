package design_pattern.strategy;

import java.util.List;

/**
 * ˫��icon�����߼�
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/11/28 16:44
 */
public class DoubleLineIconSortStrategy implements SortStrategy{
    @Override
    public List<String> sort(List<String> icons) {
        System.out.println("ִ��˫��icon�����߼�");
        return null;
    }
}
