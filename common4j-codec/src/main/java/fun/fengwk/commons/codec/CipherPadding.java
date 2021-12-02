package fun.fengwk.commons.codec;

/**
 * 填充模式。
 * 
 * @author fengwk
 */
public enum CipherPadding {

    /**
     * 不采用填充模式。
     */
    NO_PADDING("NoPadding"),
    
    /**
     * XML加密语法和处理文档中有详细描述。
     */
    ISO_10126_PADDING("ISO10126Padding"),
    
    /**
     * PKCS1，RSA算法使用。
     */
    PKCS1_PADDING("PKCS1Padding"),
    
    /**
     * PKCS5，RSA算法使用。
     */
    PKCS5_PADDING("PKCS5Padding"),
    
    /**
     * SSL Protocol Version 3.0。
     */
    SSL3_PADDING("SSL3Padding");
    
    private final String name;
    
    private CipherPadding(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
}
