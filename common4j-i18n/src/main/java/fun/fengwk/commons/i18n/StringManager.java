package fun.fengwk.commons.i18n;

import ognl.OgnlException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

/**
 * 本地化字符串管理器。
 * 
 * @author fengwk
 */
public class StringManager {

    private static final Logger LOG = LoggerFactory.getLogger(StringManager.class);

    private final ResourceBundle resourceBundle;
    private final String keyPrefix;

    /**
     * 构造字符串管理器
     * 
     * @param resourceBundle
     */
    public StringManager(ResourceBundle resourceBundle) {
        this(resourceBundle, null);
    }
    
    /**
     * 构造字符串管理器
     * 
     * @param resourceBundle
     * @param keyPrefix
     */
    public StringManager(ResourceBundle resourceBundle, String keyPrefix) {
        this.resourceBundle = Objects.requireNonNull(resourceBundle);
        this.keyPrefix = keyPrefix;
    }

    /**
     * 通过key获取字符串。
     * 
     * @param key
     * @return
     */
    public String getString(String key) {
        return getString(key, Collections.emptyMap());
    }
    
    /**
     * 通过key获取字符串。
     * 
     * @param key
     * @param ctx
     * @return
     */
    public String getString(String key, Map<String, Object> ctx) {
        Object obj = resourceBundle.getString(realKey(key));
        if (obj == null) {
            LOG.warn("Cannot found string by key '{}'", key);
            return null;
        }
        
        String str = String.valueOf(obj);

        try {
            return ExpressionParser.parse(str, ctx);
        } catch (OgnlException e) {
            throw new IllegalArgumentException(e);
        }
    }
    
    private String realKey(String key) {
        return keyPrefix == null ? key : keyPrefix + key;
    }
    
}
