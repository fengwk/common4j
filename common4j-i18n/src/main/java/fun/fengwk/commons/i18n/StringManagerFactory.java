package fun.fengwk.commons.i18n;

import java.lang.reflect.Proxy;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 字符串管理器工厂。
 * 
 * @author fengwk
 */
public class StringManagerFactory {
    
    private static final String EMPTY = "";
    private static final String DOT = ".";
    
    private final ResourceBundle resourceBundle;
    private final ConcurrentMap<String, StringManager> stringManagerRegistry = new ConcurrentHashMap<>();
    private final ConcurrentMap<Class<?>, Object> stringManagerProxyRegistry = new ConcurrentHashMap<>();
    
    public StringManagerFactory(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }
    
    /**
     * 直接构建StringManager。
     * 
     * @return
     */
    public StringManager getStringManager() {
        return stringManagerRegistry.computeIfAbsent(EMPTY, k -> new StringManager(resourceBundle, null));
    }

    /**
     * 通过key前缀构建StringManager。
     * 
     * @param keyPrefix
     * @return
     */
    public StringManager getStringManager(String keyPrefix) {
        if (keyPrefix == null || keyPrefix.isEmpty()) {
            return getStringManager();
        }
        
        return stringManagerRegistry.computeIfAbsent(keyPrefix, k -> new StringManager(resourceBundle, k));
    }
    
    /**
     * 使用类全路径作为key前缀构建StringManager。
     * 
     * @param keyPrefixClass
     * @return
     */
    public StringManager getStringManager(Class<?> keyPrefixClass) {
        String keyPrefix = keyPrefixClass.getName() + DOT;
        return getStringManager(keyPrefix);
    }
    
    /**
     * 获取StringManager代理对象以简化编码方式，代理对象应该是一个接口，
     * 其方法将会映射为key，参数将被映射为上下文参数，使用{@link Name}注解可显示指定上下文参数名称。
     * 
     * @param <T>
     * @param proxyClass
     * @param keyPrefix
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> T getStringManagerProxy(Class<T> proxyClass, String keyPrefix) {
        return (T) stringManagerProxyRegistry.computeIfAbsent(
                proxyClass, k -> createProxy(proxyClass, getStringManager(keyPrefix)));
    }
    
    /**
     * 获取StringManager代理对象以简化编码方式，代理对象应该是一个接口，
     * 其方法将会映射为key，参数将被映射为上下文参数，使用{@link Name}注解可显示指定上下文参数名称。
     * 
     * @param <T>
     * @param proxyClass
     * @param keyPrefixClass
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> T getStringManagerProxy(Class<T> proxyClass, Class<?> keyPrefixClass) {
        return (T) stringManagerProxyRegistry.computeIfAbsent(
                proxyClass, k -> createProxy(proxyClass, getStringManager(keyPrefixClass)));
    }
    
    /**
     * 获取StringManager代理对象以简化编码方式，代理对象应该是一个接口，
     * 其方法将会映射为key，参数将被映射为上下文参数，使用{@link Name}注解可显示指定上下文参数名称。
     * 
     * @param <T>
     * @param proxyClass
     * @return
     */
    public <T> T getStringManagerProxy(Class<T> proxyClass) {
        return getStringManagerProxy(proxyClass, proxyClass);
    }
    
    private Object createProxy(Class<?> proxyClass, StringManager stringManager) {
        return Proxy.newProxyInstance(
                ClassLoaderUtils.getDefault(), 
                new Class<?>[] { proxyClass }, 
                new StringManagerProxyInvocationHandler(stringManager));
    }
    
}
