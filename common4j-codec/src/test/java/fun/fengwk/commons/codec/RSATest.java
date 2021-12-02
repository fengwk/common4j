package fun.fengwk.commons.codec;

import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.security.KeyPair;
import java.util.Arrays;

/**
 * 
 * @author fengwk
 */
public class RSATest {
    
    @Test
    public void test() throws IllegalBlockSizeException, BadPaddingException {
        KeyPair keyPair = CipherUtils.generateRsaKeyPair();
        
        RSAPublicKey publicKeyCodec = new RSAPublicKey(keyPair.getPublic().getEncoded());
        RSAPrivateKey privateKeyCodec = new RSAPrivateKey(keyPair.getPrivate().getEncoded());
        
        byte[] content = StringUtils.getBytesUtf8("hello world");
        
        assert Arrays.equals(content, privateKeyCodec.decrypt(publicKeyCodec.encrypt(content)));
        assert Arrays.equals(content, publicKeyCodec.decrypt(privateKeyCodec.encrypt(content)));
    }

}
