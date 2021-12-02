package fun.fengwk.commons.util;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * {@link Ordered}用于标识对象的优先级特征。
 * 
 * @author fengwk
 */
public interface Ordered {

    /**
     * 最高优先级。
     */
    int HIGHEST_PRECEDENCE = Integer.MIN_VALUE;

    /**
     * 最低优先级。
     */
    int LOWEST_PRECEDENCE = Integer.MAX_VALUE;
    
    /**
     * 默认优先级。
     */
    int DEFAULT_PRECEDENCE = 5;
    
    /**
     * 获取当前对象优先级，返回值越小优先级越高。
     * 
     * @return
     */
    default int getOrder() {
        return DEFAULT_PRECEDENCE;
    }
    
    /**
     * 将指定集合按{@link Ordered}排序。
     * 
     * @param <E>
     * @param collection
     * @return
     */
    static <E extends Ordered> List<E> sort(Collection<E> collection) {
        return collection.stream().sorted(Comparator.comparing(Ordered::getOrder)).collect(Collectors.toList());
    }
    
}
