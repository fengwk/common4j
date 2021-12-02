package fun.fengwk.commons.codec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;

/**
 * 
 * @author fengwk
 */
public abstract class AbstractCodec {
    
    public byte[] encrypt(byte[] input) {
        return doFinal(getEncryptor(), Cipher.ENCRYPT_MODE, input);
    }
    
    public byte[] decrypt(byte[] input) {
        return doFinal(getDecryptor(), Cipher.DECRYPT_MODE, input);
    }
    
    private byte[] doFinal(Cipher cipher, int opmode, byte[] input) {
        try {
            return cipher.doFinal(input);
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            throw new CodecException(e);
        }
    }

    /**
     * 获取编码器。
     * 
     * @return
     */
    protected abstract Cipher getEncryptor();
    
    /**
     * 获取解码器。
     * 
     * @return
     */
    protected abstract Cipher getDecryptor();
    
}
