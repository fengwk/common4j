package fun.fengwk.commons.idgen;

import java.util.UUID;

/**
 * 该生成器将生成32位的UUID。
 * 
 * @author fengwk
 */
public class UUIDGenerator implements IdGenerator<String> {

    private static final char TRIM = '-';
    
    @Override
    public String next() {
        String uuid = UUID.randomUUID().toString();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < uuid.length(); i++) {
            if (uuid.charAt(i) != TRIM) {
                sb.append(uuid.charAt(i));
            }
        }
        return sb.toString();
    }

}
