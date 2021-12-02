package fun.fengwk.commons.i18n;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

/**
 * 
 * @author fengwk
 */
public class PropertiesResourceBundleLoader implements ResourceBundleLoader {

    private static final Logger LOG = LoggerFactory.getLogger(PropertiesResourceBundleLoader.class);

    private final ClassLoader classLoader;
    
    public PropertiesResourceBundleLoader() {
        this(ClassLoaderUtils.getDefault());
    }
    
    public PropertiesResourceBundleLoader(ClassLoader classLoader) {
        this.classLoader = Objects.requireNonNull(classLoader);
    }

    @Override
    public ResourceBundle load(String baseName, Locale targetLocale) throws IOException {
        return new SimpleResourceBundle(laodProperties(baseName, targetLocale));
    }
    
    /**
     * @throws IOException 
     * @see java.util.ResourceBundle.Control#newBundle(String, Locale, String, ClassLoader, boolean)
     */
    private Map<String, String> laodProperties(String baseName, Locale targetLocale) throws IOException {
        String bundleName = toBundleName(baseName, targetLocale);
        String resourceName = toResourceName(bundleName, "properties");
        try {
            return AccessController.doPrivileged(
                new PrivilegedExceptionAction<Map<String, String>>() {
                    public Map<String, String> run() throws IOException {
                        Map<String, String> properties = new LinkedHashMap<>();
                        Enumeration<URL> resources = classLoader.getResources(resourceName);

                        if (!resources.hasMoreElements()) {
                            LOG.warn("Cannot found resource bundle by resource name '{}'", resourceName);
                        }

                        while (resources.hasMoreElements()) {
                            URL url = resources.nextElement();
                            if (url != null) {
                                URLConnection connection = url.openConnection();
                                if (connection != null) {
                                    connection.setUseCaches(false);
                                    try (InputStream is = connection.getInputStream()) {
                                        Properties p = new Properties();
                                        p.load(is);
                                        for (String k : p.stringPropertyNames()) {
                                            properties.putIfAbsent(k, p.getProperty(k));
                                        }
                                    }
                                }
                            }
                        }
                        
                        return properties;
                    }
                });
        } catch (PrivilegedActionException e) {
            throw (IOException) e.getException();
        }
    }
    
    /**
     * @see java.util.ResourceBundle.Control#toBundleName(String, Locale)
     */
    public String toBundleName(String baseName, Locale locale) {
        if (locale == Locale.ROOT) {
            return baseName;
        }

        String language = locale.getLanguage();
        String script = locale.getScript();
        String country = locale.getCountry();
        String variant = locale.getVariant();

        if (language == "" && country == "" && variant == "") {
            return baseName;
        }

        StringBuilder sb = new StringBuilder(baseName);
        sb.append('_');
        if (script != "") {
            if (variant != "") {
                sb.append(language).append('_').append(script).append('_').append(country).append('_').append(variant);
            } else if (country != "") {
                sb.append(language).append('_').append(script).append('_').append(country);
            } else {
                sb.append(language).append('_').append(script);
            }
        } else {
            if (variant != "") {
                sb.append(language).append('_').append(country).append('_').append(variant);
            } else if (country != "") {
                sb.append(language).append('_').append(country);
            } else {
                sb.append(language);
            }
        }
        return sb.toString();

    }
    
    /**
     * @see java.util.ResourceBundle.Control#toResourceName(String, String)
     */
    public final String toResourceName(String bundleName, String suffix) {
        StringBuilder sb = new StringBuilder(bundleName.length() + 1 + suffix.length());
        sb.append(bundleName.replace('.', '/')).append('.').append(suffix);
        return sb.toString();
    }

}
