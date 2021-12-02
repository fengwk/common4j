package fun.fengwk.commons.util;

import org.junit.Test;

/**
 * 
 * @author fengwk
 */
public class AntPathMatcherTest {

    @Test
    public void testMatch1() {
        assert new AntPathMatcher("a/**", "/").match("a");
    }
    
    @Test
    public void testMatch2() {
        assert new AntPathMatcher("a/b*", "/").match("a/b");
    }
    
    @Test
    public void testMatch3() {
        assert new AntPathMatcher("/**", "/").match("/");
    }
    
    @Test
    public void testMatch4() {
        assert new AntPathMatcher("fun/**/fengwk/**/**/**??.clas?", "/").match("fun/fengwk/util/xx.class");
    }
    
    @Test
    public void testMatch5() {
        assert new AntPathMatcher("fun/fengwk/easymall/user/core/****/*?????.clas?", "/").match("fun/fengwk/easymall/user/core/service/UserSecurityService.class");
    }
    
    @Test
    public void testMatch6() {
        assert new AntPathMatcher("", "/").match("");
    }
    
    @Test
    public void testMatch7() {
        assert !new AntPathMatcher("a/b/c", "/").match("a/b");
    }
    
    @Test
    public void testMatch8() {
        assert !new AntPathMatcher("a/**/c", "/").match("a/b");
    }
    
    @Test
    public void testMatch9() {
        assert new AntPathMatcher("a/**/c", "/").match("a/c");
    }
    
    @Test
    public void testMatch10() {
        assert new AntPathMatcher("**", "/").match("a");
    }
    
}
