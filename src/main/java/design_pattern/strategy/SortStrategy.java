package design_pattern.strategy;

import java.util.List;

/**
 * ÌÔ±¦Ê×Ò³iconÇøÅÅĞò²ßÂÔ
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/11/28 16:39
 */
public interface SortStrategy {
    /**
     * ÅÅĞò²ßÂÔ
     */
    List<String> sort(List<String> icons);
}
