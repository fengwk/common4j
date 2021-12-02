package fun.fengwk.commons.codec;

import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.util.Arrays;

/**
 * 
 * @author fengwk
 */
public class DESTest {
    
    @Test
    public void test() throws IllegalBlockSizeException, BadPaddingException {
        byte[] secretKey = StringUtils.getBytesUtf8("11111111");
        byte[] content = StringUtils.getBytesUtf8("hello world");
        
        DES desCodec = new DES(secretKey);
        byte[] aesEncryptedBytes = desCodec.encrypt(content);
        byte[] cleartextBytes = desCodec.decrypt(aesEncryptedBytes);
        
        assert Arrays.equals(content, cleartextBytes);
    }

}
