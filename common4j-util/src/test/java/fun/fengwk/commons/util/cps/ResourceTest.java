package fun.fengwk.commons.util.cps;

import org.junit.Test;

/**
 * 
 * @author fengwk
 */
public class ResourceTest {

    @Test
    public void test() {
        assert "Resource.class".equals(new Resource(null, "fun/fengwk/Resource.class").getName());
        assert "Resource.class".equals(new Resource(null, "Resource.class").getName());
        assert "".equals(new Resource(null, "").getName());
    }
    
}
