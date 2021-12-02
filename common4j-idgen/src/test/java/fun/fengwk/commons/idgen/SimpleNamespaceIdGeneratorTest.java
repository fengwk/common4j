package fun.fengwk.commons.idgen;

import org.junit.Test;

/**
 * @author fengwk
 */
public class SimpleNamespaceIdGeneratorTest {

    @Test
    public void test() {
        SimpleNamespaceIdGenerator<Long> idGenerator = new SimpleNamespaceIdGenerator<Long>(
                ns -> new SnowflakesIdGenerator(System.currentTimeMillis(), 0));
        long id1 = idGenerator.next(SimpleNamespaceIdGeneratorTest.class);
        long id2 = idGenerator.next(SimpleNamespaceIdGeneratorTest.class);
        assert id2 > id1;
    }

}
