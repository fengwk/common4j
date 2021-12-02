package fun.fengwk.commons.codec;

import javax.crypto.Cipher;

/**
 * 
 * @author fengwk
 */
public class DES extends AbstractCodec {

    private final byte[] secretKey;
    
    public DES(byte[] secretKey) {
        this.secretKey = secretKey;
    }

    @Override
    protected Cipher getEncryptor() {
        return CipherUtils.getDesEncryptor(secretKey);
    }

    @Override
    protected Cipher getDecryptor() {
        return CipherUtils.getDesDecryptor(secretKey);
    }
    
}
