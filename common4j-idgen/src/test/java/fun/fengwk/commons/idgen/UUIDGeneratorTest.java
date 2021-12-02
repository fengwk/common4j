package fun.fengwk.commons.idgen;

import org.junit.Test;

/**
 * 
 * @author fengwk
 */
public class UUIDGeneratorTest {

    @Test
    public void test() {
        UUIDGenerator uuidGenerator = new UUIDGenerator();
        assert uuidGenerator.next().length() == 32;
    }
    
}
