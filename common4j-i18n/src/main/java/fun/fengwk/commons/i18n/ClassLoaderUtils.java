package fun.fengwk.commons.i18n;

/**
 * 
 * @author fengwk
 */
class ClassLoaderUtils {

    private ClassLoaderUtils() {}
    
    static ClassLoader getDefault() {
        ClassLoader cl = null;
        try {
            cl = Thread.currentThread().getContextClassLoader();
        } catch (Throwable ignore) {}
        
        if (cl == null) {
            cl = StringManagerFactory.class.getClassLoader();
        }
        
        if (cl == null) {
            cl = ClassLoader.getSystemClassLoader();
        }
        
        return cl;
    }
    
}
