package fun.fengwk.commons.i18n;

import java.util.Map;
import java.util.Objects;

/**
 * 
 * @author fengwk
 */
public class SimpleResourceBundle implements ResourceBundle {

    private final Map<String, String> properties;

    public SimpleResourceBundle(Map<String, String> properties) {
        this.properties = Objects.requireNonNull(properties);
    }

    @Override
    public String getString(String key) {
        return properties.get(key);
    }
    
}
