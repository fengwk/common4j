package fun.fengwk.commons.codec;

/**
 * 工作模式。
 * 
 * @author fengwk
 */
public enum CipherAlgorithm {
    
    RSA("RSA"),
    DES("DES"),
    DE_SEDE("DESede"),
    DE_SEDE_WRAP("DESedeWrap"),
    PBE_WITH_MD5_AND_DES("PBEWithMD5AndDES"),
    PBE_WITH_MD5_AND_TRIPLE_DES("PBEWithMD5AndTripleDES"),
    PBE_WITH_SHA1_AND_DE_SEDE("PBEWithSHA1AndDESede"),
    PBE_WITH_SHA1_AND_RC2_40("PBEWithSHA1AndRC2_40"),
    PBE_WITH_SHA1_AND_RC2_128("PBEWithSHA1AndRC2_128"),
    PBE_WITH_SHA1_AND_RC4_40("PBEWithSHA1AndRC4_40"),
    PBE_WITH_SHA1_AND_RC4_128("PBEWithSHA1AndRC4_128"),
    PBE_WITH_HMAC_SHA1_AND_AES_128("PBEWithHmacSHA1AndAES_128"),
    PBE_WITH_HMAC_SHA224_AND_AES_128("PBEWithHmacSHA224AndAES_128"),
    PBE_WITH_HMAC_SHA256_AND_AES_128("PBEWithHmacSHA256AndAES_128"),
    PBE_WITH_HMAC_SHA384_AND_AES_128("PBEWithHmacSHA384AndAES_128"),
    PBE_WITH_HMAC_SHA512_AND_AES_128("PBEWithHmacSHA512AndAES_128"),
    PBE_WITH_HMAC_SHA1_AND_AES_256("PBEWithHmacSHA1AndAES_256"),
    PBE_WITH_HMAC_SHA224_AND_AES_256("PBEWithHmacSHA224AndAES_256"),
    PBE_WITH_HMAC_SHA256_AND_AES_256("PBEWithHmacSHA256AndAES_256"),
    PBE_WITH_HMAC_SHA384_AND_AES_256("PBEWithHmacSHA384AndAES_256"),
    PBE_WITH_HMAC_SHA512_AND_AES_256("PBEWithHmacSHA512AndAES_256"),
    BLOWFISH("Blowfish"),
    AES("AES"),
    AES_128("AES_128"),
    AES_192("AES_192"),
    AES_256("AES_256"),
    AES_WRAP("AESWrap"),
    AES_WRAP_128("AESWrap_128"),
    AES_WRAP_192("AESWrap_192"),
    AES_WRAP_256("AESWrap_256"),
    RC2("RC2"),
    ARCFOUR("ARCFOUR");
    
    private final String name;
    
    private CipherAlgorithm(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
}
