package fun.fengwk.commons.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * {@link AntPathMatcher}是一个Ant风格的路径匹配器。
 * 
 * @author fengwk
 */
public class AntPathMatcher {
    
    public static final String DEFAULT_PATH_SEPARATOR = "/";
    public static final String ANY_SINGLE_CHARACTER = "?";
    public static final String ANY_CHARACTER = "*";
    public static final String ANY_LAYER = "**";

    private static final char C_ANY_SINGLE_CHARACTER = ANY_SINGLE_CHARACTER.charAt(0);
    private static final char C_ANY_CHARACTER = ANY_CHARACTER.charAt(0);

    private final List<String> parts;
    private final String separator;
    
    public AntPathMatcher(String pattern) {
        this(pattern, DEFAULT_PATH_SEPARATOR);
    }
    
    public AntPathMatcher(String pattern, String separator) {
        this.parts = split(pattern, separator);
        this.separator = separator;
    }
    
    /**
     * 判断传入path是否与当前模式匹配。
     * 
     * @param path
     * @return
     */
    public boolean match(String path) {
        List<String> pathParts = split(path, separator);
        // i指向下一个要比较的parts位置
        // j指向下一个要比较的pathParts位置
        int i = 0, j = 0;
        while (i < parts.size()) {
            if (ANY_LAYER.equals(parts.get(i))) {
                // 将i定位到之后parts中第一个不是ANY_LAYER的位置或者结尾
                while (++i < parts.size() && ANY_LAYER.equals(parts.get(i))) {}
                
                if (i == parts.size()) {
                    // 直到结尾都是ANY_LAYER
                    return true;
                } else {
                    // 将j定位到之后pathParts中第一个与parts.get(i)匹配的位置，或者直到结尾
                    while (j < pathParts.size() && !matchPart(parts.get(i), pathParts.get(j))) {
                        j++;
                    }
                    
                    if (j < pathParts.size()) {
                        i++;
                        j++;
                    } else {
                        break;
                    }
                }
            } else if (j < pathParts.size() && matchPart(parts.get(i), pathParts.get(j))) {
                i++;
                j++;
            } else {
                break;
            }
        }
        return i == parts.size() && j == pathParts.size();
    }
    
    private boolean matchPart(String part, String pathPart) {
        // i指向下一个要比较的part字符
        // j指向下一个要比较的pathPart字符
        int i = 0, j = 0;
        while (i < part.length()) {
            if (part.charAt(i) == C_ANY_SINGLE_CHARACTER) {
                i++;
                j++;
            } else if (part.charAt(i) == C_ANY_CHARACTER) {
                // 将i定位到后边part中第一个不为SINGLE_CHAR或ANY_CHAR的字符或结尾，并且记录期间跳过的SINGLE_CHAR数量
                int skipSingleCharCount = 0;
                while (++i < part.length() && (part.charAt(i) == C_ANY_CHARACTER || part.charAt(i) == C_ANY_SINGLE_CHARACTER)) {
                    if (part.charAt(i) == C_ANY_SINGLE_CHARACTER) {
                        skipSingleCharCount++;
                    }
                }
                
                if (i == part.length()) {
                    // i已经到达结尾，比较pathPart重剩余字符数是否能够容纳跳过的SINGLE_CHAR
                    return pathPart.length() - j >= skipSingleCharCount;
                } else {
                    // 还未到结尾，将j定位到之后pathPart中第一个与part.charAt(i)匹配的字符上，或者直到结尾
                    // 如果定位到结尾循环将退出，如果定位到匹配位置，下一轮循环将处理该位置
                    int startJ = j;
                    while (j < pathPart.length() && part.charAt(i) != pathPart.charAt(j)) {
                        j++;
                    }
                    
                    // 检查startJ到j跳过元素否能容纳跳过的SINGLE_CHAR
                    if (j - startJ < skipSingleCharCount) {
                        return false;
                    } else {
                        // 检查在上一步骤的while循环中是否找到了part.charAt(i) == pathPart.charAt(j)
                        if (j < pathPart.length()) {
                            i++;
                            j++;
                        } else {
                            break;
                        }
                    }
                }
            } else if (j < pathPart.length() && part.charAt(i) == pathPart.charAt(j)) {
                i++;
                j++;
            } else {
                break;
            }
        }
        return i == part.length() && j == pathPart.length();
    }
    
    private List<String> split(String pattern, String separator) {
        List<String> parts = new ArrayList<>();
        int offset = 0;
        int i;
        while ((i = pattern.indexOf(separator, offset)) != -1) {
            parts.add(pattern.substring(offset, i));
            offset = i + separator.length();
        }
        parts.add(pattern.substring(offset));
        return parts;
    }

    @Override
    public int hashCode() {
        return Objects.hash(parts, separator);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        AntPathMatcher other = (AntPathMatcher) obj;
        return Objects.equals(parts, other.parts) 
                && Objects.equals(separator, other.separator);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < parts.size(); i++) {
            sb.append(parts.get(i));
            if (i < parts.size() - 1) {
                sb.append(separator);
            }
        }
        return sb.toString();
    }
    
}
