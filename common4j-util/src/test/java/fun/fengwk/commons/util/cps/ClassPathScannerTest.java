package fun.fengwk.commons.util.cps;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * 
 * @author fengwk
 */
public class ClassPathScannerTest {

    @Test
    public void test() throws IOException {
        ClassPathScanner cps = new ClassPathScanner();
        List<Resource> urls = cps.scan("**/*.class");
        assert !urls.isEmpty();
    }
    
}
