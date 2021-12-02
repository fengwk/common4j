package fun.fengwk.commons.util;

import org.junit.Test;

/**
 * 
 * @author fengwk
 */
public class PropertyTest {

    @Test
    public void test() {
        assert Property.of(User::getAddr).in(Address::getCity).getPath().equals("addr.city");
    }
    
    static class User {
        
        Address addr;

        Address getAddr() {
            return addr;
        }
        
    }
    
    static class Address {
        
        String city;
        String street;

        String getCity() {
            return city;
        }

        String getStreet() {
            return street;
        }
        
    }
    
}
