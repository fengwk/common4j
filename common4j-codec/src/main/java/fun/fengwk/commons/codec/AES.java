package fun.fengwk.commons.codec;

import javax.crypto.Cipher;

/**
 * 
 * @author fengwk
 */
public class AES extends AbstractCodec {

    private final byte[] secretKey;
    
    public AES(byte[] secretKey) {
        this.secretKey = secretKey;
    }

    @Override
    protected Cipher getEncryptor() {
        return CipherUtils.getAesEncryptor(secretKey);
    }

    @Override
    protected Cipher getDecryptor() {
        return CipherUtils.getAesDecryptor(secretKey);
    }
    
}
