package fun.fengwk.commons.util.cps;

import fun.fengwk.commons.util.AntPathMatcher;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author fengwk
 */
public class FileScanDelegateTest {
    
    @Test
    public void test() throws IOException {
        AntPathMatcher pm = new AntPathMatcher("**/*.class", "/");
        List<Resource> collector = new ArrayList<>();
        URL rootDir = FileScanDelegateTest.class.getClassLoader().getResource("");
        FileScanDelegate fsd = new FileScanDelegate();
        fsd.scan(pm, collector, "", rootDir);
        boolean result = false;
        for (Resource resource : collector) {
            result |= resource.getURL().toString().endsWith(FileScanDelegateTest.class.getSimpleName() + ".class");
        }
        assert result;
    }

}
