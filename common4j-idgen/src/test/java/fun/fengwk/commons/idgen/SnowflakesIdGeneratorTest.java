package fun.fengwk.commons.idgen;

import org.junit.Test;

/**
 * 
 * @author fengwk
 */
public class SnowflakesIdGeneratorTest {

    @Test
    public void test() {
        SnowflakesIdGenerator snowflakesIdGenerator = new SnowflakesIdGenerator(System.currentTimeMillis(), 0);
        long id1 = snowflakesIdGenerator.next();
        long id2 = snowflakesIdGenerator.next();
        assert id2 > id1;
    }
    
}
