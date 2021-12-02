package fun.fengwk.commons.i18n;

import java.io.IOException;
import java.util.Locale;

/**
 * 资源包加载器。
 * 
 * @author fengwk
 */
public interface ResourceBundleLoader {

    /**
     * 加载资源包。
     * 
     * @param baseName
     * @param targetLocale
     * @return
     * @throws IOException
     */
    ResourceBundle load(String baseName, Locale targetLocale) throws IOException;
    
}
