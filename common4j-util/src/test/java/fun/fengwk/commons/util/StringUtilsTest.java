package fun.fengwk.commons.util;

import org.junit.Test;

/**
 * 
 * @author fengwk
 */
public class StringUtilsTest {

    @Test
    public void testIsEmpty() {
        assert StringUtils.isEmpty(null);
        assert StringUtils.isEmpty("");
        assert !StringUtils.isEmpty("fengwk");
    }
    
    @Test
    public void testIsBlank() {
        assert StringUtils.isBlank(null);
        assert StringUtils.isBlank("");
        assert StringUtils.isBlank("  ");
        assert !StringUtils.isBlank("fengwk");
    }
    
}
