package fun.fengwk.commons.util;

import org.junit.Test;

/**
 * 
 * @author fengwk
 */
public class RefTest {

    @Test
    public void test1() {
        assert Ref.empty().value == null;
        assert Ref.of(1).value == 1;
    }
    
}
