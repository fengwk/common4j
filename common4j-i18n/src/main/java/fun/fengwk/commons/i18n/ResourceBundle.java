package fun.fengwk.commons.i18n;

/**
 * 资源包。
 * 
 * @author fengwk
 */
public interface ResourceBundle {
    
    /**
     * 通过键获取关联的字符串。
     * 
     * @param key
     * @return
     */
    String getString(String key);

}
