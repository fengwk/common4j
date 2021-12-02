package fun.fengwk.commons.util.cps;

import fun.fengwk.commons.util.AntPathMatcher;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * 
 * @author fengwk
 */
abstract class ScanDelegate {

    abstract void scan(AntPathMatcher pathMatcher, List<Resource> collector, String rootClasspath, URL rootUrl) throws IOException;

}
