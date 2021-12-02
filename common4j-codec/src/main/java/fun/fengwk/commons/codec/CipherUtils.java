package fun.fengwk.commons.codec;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * 
 * @author fengwk
 */
public class CipherUtils {

    private CipherUtils() {}
    
    /**
     * 获取DES加密器。
     * 
     * @param secretKey 长度必须为8的倍数
     * @return
     */
    public static Cipher getDesEncryptor(byte[] secretKey) {
        DESKeySpec desKeySpec = getDESKeySpec(secretKey);
        SecretKey securekey = getSecretKey(CipherAlgorithm.DES, desKeySpec);
        Cipher cipher = getCipher(CipherAlgorithm.DES);
        init(cipher, Cipher.ENCRYPT_MODE, securekey);
        return cipher;
    }
    
    /**
     * 获取DES解密器。
     * 
     * @param secretKey 长度必须为8的倍数
     * @return
     */
    public static Cipher getDesDecryptor(byte[] secretKey) {
        DESKeySpec desKeySpec = getDESKeySpec(secretKey);
        SecretKey securekey = getSecretKey(CipherAlgorithm.DES, desKeySpec);
        Cipher cipher = getCipher(CipherAlgorithm.DES);
        init(cipher, Cipher.DECRYPT_MODE, securekey);
        return cipher;
    }
    
    /**
     * 获取AES加密器。
     * 
     * @param secretKey
     * @return
     */
    public static Cipher getAesEncryptor(byte[] secretKey) {
        KeyGenerator keyGenerator = getKeyGenerator(CipherAlgorithm.AES);
        keyGenerator.init(128, getSecureRandom(secretKey));
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyGenerator.generateKey().getEncoded(), CipherAlgorithm.AES.getName());
        Cipher cipher = getCipher(CipherAlgorithm.AES);
        init(cipher, Cipher.ENCRYPT_MODE, secretKeySpec);
        return cipher;
    }
    
    /**
     * 获取AES解密器。
     * 
     * @param secretKey
     * @return
     */
    public static Cipher getAesDecryptor(byte[] secretKey) {
        KeyGenerator keyGenerator = getKeyGenerator(CipherAlgorithm.AES);
        keyGenerator.init(128, getSecureRandom(secretKey));
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyGenerator.generateKey().getEncoded(), CipherAlgorithm.AES.getName());
        Cipher cipher = getCipher(CipherAlgorithm.AES);
        init(cipher, Cipher.DECRYPT_MODE, secretKeySpec);
        return cipher;
    }
    
    /**
     * 生成RSA公私钥对。
     * 
     * @return
     */
    public static KeyPair generateRsaKeyPair() {
        return generateRsaKeyPair(2048);
    }
    
    /**
     * 生成RSA公私钥对。
     * 
     * @param keySize 大于等于512，推荐使用2048
     * @return
     */
    public static KeyPair generateRsaKeyPair(int keySize) {
        KeyPairGenerator keyPairGenerator = getKeyPairGenerator(CipherAlgorithm.RSA);
        keyPairGenerator.initialize(keySize);
        return keyPairGenerator.generateKeyPair();
    }
    
    /**
     * 生成RSA公钥。
     * 
     * @param publicKey
     * @return
     */
    public static RSAPublicKey generateRsaPublicKey(byte[] publicKey) {
        X509EncodedKeySpec spec = new X509EncodedKeySpec(publicKey);
        KeyFactory keyFactory = getKeyFactory(CipherAlgorithm.RSA);
        return (RSAPublicKey) generatePublicKey(keyFactory, spec);
    }
    
    /**
     * 生成RSA私钥。
     * 
     * @param privateKey
     * @return
     */
    public static RSAPrivateKey generateRsaPrivateKey(byte[] privateKey) {
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(privateKey);
        KeyFactory keyFactory = getKeyFactory(CipherAlgorithm.RSA);
        return (RSAPrivateKey) generatePrivateKey(keyFactory, spec);
    }
    
    /**
     * 获取RSA加密器。
     * 
     * @param key 公钥或私钥
     * @return
     */
    public static Cipher getRsaEncryptor(Key key) {
        Cipher cipher = getCipher(CipherAlgorithm.RSA);
        init(cipher, Cipher.ENCRYPT_MODE, key);
        return cipher;
    }
    
    /**
     * 获取RSA解密器。
     * 
     * @param key 公钥或私钥
     * @return
     */
    public static Cipher getRsaDecryptor(Key key) {
        Cipher cipher = getCipher(CipherAlgorithm.RSA);
        init(cipher, Cipher.DECRYPT_MODE, key);
        return cipher;
    }
    
    private static Cipher getCipher(CipherAlgorithm algo) {
        return getCipher(new CipherTransformation(algo));
    }
    
    private static Cipher getCipher(CipherTransformation trans) {
        try {
            return Cipher.getInstance(trans.getTransformation());
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new IllegalArgumentException(e);
        }
    }
    
    private static DESKeySpec getDESKeySpec(byte[] secretKey) {
        try {
            return new DESKeySpec(secretKey);
        } catch (InvalidKeyException e) {
            throw new IllegalArgumentException(e);
        }
    }
    
    private static SecretKey getSecretKey(CipherAlgorithm algo, DESKeySpec desKeySpec) {
        try {
            return SecretKeyFactory.getInstance(algo.getName()).generateSecret(desKeySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new IllegalArgumentException(e);
        }
    }
    
    private static KeyGenerator getKeyGenerator(CipherAlgorithm algo) {
        try {
            return KeyGenerator.getInstance(algo.getName());
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private static SecureRandom getSecureRandom(byte[] secretKey) {
        // JAVA AES加解密在Linux报错javax.crypto.BadPaddingException: Given final block not properly padded.
        // https://blog.csdn.net/qq_23888451/article/details/94719836
        SecureRandom sr;
        try {
            sr = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }
        sr.setSeed(secretKey);
        return sr;
    }
    
    private static void init(Cipher cipher, int opmode, SecretKeySpec secretKeySpec) {
        try {
            cipher.init(opmode, secretKeySpec);
        } catch (InvalidKeyException e) {
            throw new IllegalArgumentException(e);
        }
    }
    
    private static void init(Cipher cipher, int opmode, Key key) {
        try {
            cipher.init(opmode, key);
        } catch (InvalidKeyException e) {
            throw new IllegalArgumentException(e);
        }
    }
    
    private static KeyPairGenerator getKeyPairGenerator(CipherAlgorithm algo) {
        try {
            return KeyPairGenerator.getInstance(algo.getName());
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }
    }
    
    private static KeyFactory getKeyFactory(CipherAlgorithm algo) {
        try {
            return KeyFactory.getInstance(algo.getName());
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }
    }
    
    private static PublicKey generatePublicKey(KeyFactory keyFactory, KeySpec keySpec) {
        try {
            return keyFactory.generatePublic(keySpec);
        } catch (InvalidKeySpecException e) {
            throw new IllegalArgumentException(e);
        }
    }
    
    private static PrivateKey generatePrivateKey(KeyFactory keyFactory, KeySpec keySpec) {
        try {
            return keyFactory.generatePrivate(keySpec);
        } catch (InvalidKeySpecException e) {
            throw new IllegalArgumentException(e);
        }
    }
    
}
