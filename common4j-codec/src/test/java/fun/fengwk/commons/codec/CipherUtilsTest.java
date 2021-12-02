package fun.fengwk.commons.codec;

import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * 
 * @author fengwk
 */
public class CipherUtilsTest {
    
    @Test
    public void testDES() throws IllegalBlockSizeException, BadPaddingException {
        byte[] secret = StringUtils.getBytesUtf8("11111111");
        String content = "hello, cipher";
        
        Cipher encryptor = CipherUtils.getDesEncryptor(secret);
        byte[] aesEncryptedBytes = encryptor.doFinal(StringUtils.getBytesUtf8(content));
        
        Cipher decryptor = CipherUtils.getDesDecryptor(secret);
        byte[] cleartextBytes = decryptor.doFinal(aesEncryptedBytes);
        
        assert content.equals(new String(cleartextBytes, StandardCharsets.UTF_8));
    }

    @Test
    public void testAES() throws IllegalBlockSizeException, BadPaddingException {
        byte[] secret = StringUtils.getBytesUtf8("fengwk");
        String content = "hello, cipher";
        
        Cipher encryptor = CipherUtils.getAesEncryptor(secret);
        byte[] aesEncryptedBytes = encryptor.doFinal(StringUtils.getBytesUtf8(content));
        
        Cipher decryptor = CipherUtils.getAesDecryptor(secret);
        byte[] cleartextBytes = decryptor.doFinal(aesEncryptedBytes);
        
        assert content.equals(new String(cleartextBytes, StandardCharsets.UTF_8));
    }
    
    @Test
    public void testGenerateRsaKeyPair() {
        KeyPair keyPair = CipherUtils.generateRsaKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();
        System.out.println("publicKey:" + CodecUtils.bytesToHexStr(publicKey.getEncoded()));
        System.out.println("privateKey:" + CodecUtils.bytesToHexStr(privateKey.getEncoded()));
    }
    
    @Test
    public void testGenerateRsaKeyPair2() {
        KeyPair keyPair = CipherUtils.generateRsaKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();
        System.out.println(CodecUtils.bytesToHexStr(publicKey.getEncoded()));
        System.out.println(CodecUtils.bytesToHexStr(privateKey.getEncoded()));
    }
    
}
