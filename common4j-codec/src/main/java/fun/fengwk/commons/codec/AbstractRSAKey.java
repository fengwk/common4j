package fun.fengwk.commons.codec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import java.io.ByteArrayOutputStream;
import java.security.Key;

/**
 * 
 * @author fengwk
 */
public abstract class AbstractRSAKey extends AbstractCodec {
    
    private final Key key;
    
    protected AbstractRSAKey(Key key) {
        this.key = key;
    }

    @Override
    public byte[] encrypt(byte[] data) {
        Cipher cipher = CipherUtils.getRsaEncryptor(key);
        return splitAndCodec(cipher, Cipher.ENCRYPT_MODE, data);
    }

    @Override
    public byte[] decrypt(byte[] data) {
        Cipher cipher = CipherUtils.getRsaDecryptor(key);
        return splitAndCodec(cipher, Cipher.DECRYPT_MODE, data);
    }
    
    @Override
    protected Cipher getEncryptor() {
        throw new UnsupportedOperationException();
    }

    @Override
    protected Cipher getDecryptor() {
        throw new UnsupportedOperationException();
    }

    private byte[] splitAndCodec(Cipher cipher, int opmode, byte[] data) {
        try {
            return doSplitAndCodec(cipher, opmode, data);
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            throw new CodecException(e);
        }
    }

    /**
     * 分割运算，RSA加密算法对于加密数据的长度是有要求的，一般来说明文长度小于等于密钥长度(byte)-11，将数据分割为合适长度再进行加密
     *
     * @param cipher
     * @param opmode
     * @param data
     * @return
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    private byte[] doSplitAndCodec(Cipher cipher, int opmode, byte[] data) throws IllegalBlockSizeException, BadPaddingException {
        int keySize = ((java.security.interfaces.RSAKey) key).getModulus().bitLength();
        // 计算单块长度
        int blockLen;
        if (opmode == Cipher.DECRYPT_MODE) {
            blockLen = keySize / 8;
        } else {
            blockLen = keySize / 8 - 11;
        }
        // 构建输出字节流
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] buff;
        while (data.length > offSet) {
            if (data.length - offSet > blockLen) {
                buff = cipher.doFinal(data, offSet, blockLen);
            } else {
                buff = cipher.doFinal(data, offSet, data.length - offSet);
            }
            out.write(buff, 0, buff.length);
            offSet += blockLen;
        }
        return out.toByteArray();
    }

}
