package fun.fengwk.commons.util;

import java.nio.charset.StandardCharsets;

/**
 * 
 * @author fengwk
 */
public class StringUtils {

    /**
     * 空白字符串。
     */
    public static final String EMPTY = "";
    
    private StringUtils() {}
    
    /**
     * 以utf-8格式编码字符串。
     * 
     * @param str
     * @return
     */
    public static byte[] getBytesUtf8(String str) {
        return str.getBytes(StandardCharsets.UTF_8);
    }
    
    /**
     * 检查字符串是否为null或{@code str.isEmpty()}为true。
     * 
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }
    
    /**
     * 检查字符串是否为null或{@code str.trim().isEmpty()}为true。
     * 
     * @param str
     * @return
     */
    public static boolean isBlank(String str) {
        return str == null || str.trim().isEmpty();
    }
    
    /**
     * 
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
    
    /**
     * 
     * @param str
     * @return
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }
    
}
