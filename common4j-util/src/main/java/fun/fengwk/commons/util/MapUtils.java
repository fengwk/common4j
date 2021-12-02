package fun.fengwk.commons.util;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 
 * @author fengwk
 */
public class MapUtils {

    private MapUtils() {}
    
    /**
     * 将映射转为并发映射。
     * 
     * @param <K>
     * @param <V>
     * @param map
     * @return
     */
    public static <K, V> ConcurrentMap<K, V> toConcurrent(Map<K, V> map) {
        return map instanceof ConcurrentMap ? (ConcurrentMap<K, V>) map : doConvertToConcurrent(map);
    }
    
    private static <K, V> ConcurrentMap<K, V> doConvertToConcurrent(Map<K, V> map) {
        ConcurrentMap<K, V> concurrentMap = new ConcurrentHashMap<>();
        
        for (Entry<K, V> e : map.entrySet()) {
            concurrentMap.put(e.getKey(), e.getValue());
        }
        
        return concurrentMap;
    }
    
}
