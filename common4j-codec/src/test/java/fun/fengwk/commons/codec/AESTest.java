package fun.fengwk.commons.codec;

import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.util.Arrays;

/**
 * 
 * @author fengwk
 */
public class AESTest {
    
    @Test
    public void test() throws IllegalBlockSizeException, BadPaddingException {
        byte[] secretKey = StringUtils.getBytesUtf8("fengwk");
        byte[] content = StringUtils.getBytesUtf8("hello world");
        
        AES aesCodec = new AES(secretKey);
        byte[] aesEncryptedBytes = aesCodec.encrypt(content);
        byte[] cleartextBytes = aesCodec.decrypt(aesEncryptedBytes);
        
        assert Arrays.equals(content, cleartextBytes);
    }

}
