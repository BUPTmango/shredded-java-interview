package design_pattern.strategy;

import java.util.List;

/**
 * �Ա���ҳicon���������
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/11/28 16:39
 */
public interface SortStrategy {
    /**
     * �������
     */
    List<String> sort(List<String> icons);
}
