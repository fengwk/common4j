package fun.fengwk.commons.idgen;

import fun.fengwk.commons.idgen.snowflakes.FixedWorkerIdClient;
import fun.fengwk.commons.idgen.snowflakes.SnowflakesIdGenerator;
import org.junit.Test;

/**
 * 
 * @author fengwk
 */
public class NamedIdGeneratorTest {

    @Test
    public void test() {
        NamedIdGenerator<Long> namedIdGenerator = 
                new NamedIdGenerator<>(new SnowflakesIdGenerator(System.currentTimeMillis(), new FixedWorkerIdClient(0L)), "myName");
        long id1 = namedIdGenerator.next();
        long id2 = namedIdGenerator.next();
        assert id2 > id1;
        
        assert namedIdGenerator.toString().equals("myName");
    }
    
}
