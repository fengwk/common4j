package fun.fengwk.commons.util.cps;

import fun.fengwk.commons.util.AntPathMatcher;
import fun.fengwk.commons.util.ClassUtils;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * 
 * 
 * @author fengwk
 */
public class ClassPathScanner {
    
    public static final String CLASSPATH_SEPARATOR = "/";
    
    private static final String ROOT_CLASS_PATH = "";

    private static final String JAR = "jar";
    private static final String JAR_URL_PREFIX = "jar:";
    private static final String JAR_URL_SEPARATOR = "!/";

    private ClassLoader classLoader;

    public ClassPathScanner() {
        this(ClassUtils.getDefaultClassLoader());
    }
    
    public ClassPathScanner(ClassLoader cl) {
        this.classLoader = Objects.requireNonNull(cl);
    }
    
    /**
     * 扫描classpath中符合指定ANT模式的资源
     * 
     * @param antPattern ant分格的路径匹配模式
     * @return
     * @throws IOException
     */
    public List<Resource> scan(String antPattern) throws IOException {
        AntPathMatcher pathMatcher = new AntPathMatcher(antPattern, CLASSPATH_SEPARATOR);
        List<Resource> collector = new ArrayList<>();
        
        String rootPath = getRootPath(antPattern);
        Set<URL> rootUrls = getRootUrls(rootPath);
        
        for (URL rootUrl : rootUrls) {
            String protocol = rootUrl.getProtocol();
            ScanDelegate delegate = ScanDelegateFactory.getInstance(protocol);
            if (delegate == null) {
                throw new IllegalStateException("Cannot support protocol " + protocol);
            }
            
            delegate.scan(pathMatcher, collector, rootPath, rootUrl);
        }
        
        return collector;
    }
    
    private String getRootPath(String pattern) {
        int i1 = pattern.indexOf(AntPathMatcher.ANY_SINGLE_CHARACTER);
        int i2 = pattern.indexOf(AntPathMatcher.ANY_CHARACTER);
        
        if (i1 != -1 || i2 != -1) {
            int i;
            if (i1 != -1 && i2 != -1) {
                i = Math.min(i1, i2);
            } else if (i1 != -1) {// i1 != -1 && i2 == -1
                i = i1;
            } else {// i1 == -1 && i2 != -1
                i = i2;
            }
            
            pattern = pattern.substring(0, i);
            i = pattern.lastIndexOf(CLASSPATH_SEPARATOR);
            if (i != -1) {
                pattern = pattern.substring(0, i);
            } else {
                pattern = ROOT_CLASS_PATH;
            }
        }
        
        return pattern;
    }
    
    private Set<URL> getRootUrls(String rootPath) throws IOException {
        Set<URL> rootUrls = new LinkedHashSet<>();
        
        Enumeration<URL> urlEnum = classLoader.getResources(rootPath);
        while (urlEnum.hasMoreElements()) {
            URL url = urlEnum.nextElement();
            rootUrls.add(url);
        }
        
        if (ROOT_CLASS_PATH.equals(rootPath)) {
            ClassLoader cl = classLoader;
            while (cl != null) {
                if (cl instanceof URLClassLoader) {
                    for (URL url : ((URLClassLoader) cl).getURLs()) {
                        // 如果URL是这种形式：file:/C:/Program%20Files/idea/ideaIU-2021.1.2.win/plugins/junit/lib/junit5-rt.jar
                        // 将其转换为JAR形式：jar:file:/C:/Program%20Files/idea/ideaIU-2021.1.2.win/plugins/junit/lib/junit5-rt.jar!/
                        String urlStr = url.toString();
                        if (urlStr.endsWith(JAR)) {
                            url = new URL(JAR_URL_PREFIX + urlStr + JAR_URL_SEPARATOR);
                        }

                        rootUrls.add(url);
                    }
                }
                cl = cl.getParent();
            }
        }
        
        return rootUrls;
    }
    
}
