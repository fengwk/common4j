package fun.fengwk.commons.generic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestClass<E extends TestClass<E>> {

    Map<List<? extends Object>, HashMap<E, Integer>> map;
    
}
