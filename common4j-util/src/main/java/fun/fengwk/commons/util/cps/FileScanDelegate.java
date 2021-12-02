package fun.fengwk.commons.util.cps;

import fun.fengwk.commons.util.AntPathMatcher;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

/**
 * 
 * @author fengwk
 */
class FileScanDelegate extends ScanDelegate {
    
    @Override
    void scan(AntPathMatcher pathMatcher, List<Resource> collector, String rootClasspath, URL rootUrl) throws IOException {
        File rootFile = toFile(rootUrl);
        doScan(pathMatcher, collector, rootClasspath, rootFile);
    }
    
    void doScan(AntPathMatcher pathMatcher, List<Resource> collector, String currentClasspath, File currentFile) throws IOException {
        if (pathMatcher.match(currentClasspath)) {
            collector.add(new Resource(toURL(currentFile), currentClasspath));
        }
        
        if (currentFile.isDirectory()) {
            File[] children = currentFile.listFiles();
            for (File child : children) {
                doScan(pathMatcher, collector, appendPath(currentClasspath, child.getName()), child);
            }
        }
    }
    
    private String appendPath(String path, String name) {
        if (path.isEmpty()) {
            return name;
        } else {
            return path + AntPathMatcher.DEFAULT_PATH_SEPARATOR + name;
        }
    }
    
    private File toFile(URL url) throws IOException {
        try {
            return new File(url.toURI());
        } catch (URISyntaxException e) {
            throw new IOException(e);
        }
    }
    
    private URL toURL(File file) throws MalformedURLException {
        return file.toURI().toURL();
    }

}
