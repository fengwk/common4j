package fun.fengwk.commons.iterators;

import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;

/**
 * @author fengwk
 */
public class AppendTest {

    @Test
    public void test1() {
        Iterator<Integer> iter = Iterators.append(Arrays.asList(1, 2).iterator(), 3);
        assert iter.next() == 1;
        assert iter.next() == 2;
        assert iter.next() == 3;
        assert !iter.hasNext();
    }

}
