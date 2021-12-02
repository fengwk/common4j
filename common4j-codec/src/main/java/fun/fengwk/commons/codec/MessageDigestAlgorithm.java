package fun.fengwk.commons.codec;

/**
 * 
 * @author fengwk
 */
public enum MessageDigestAlgorithm {
    
    /* copy from org.apache.commons.codec.digest.MessageDigestAlgorithms */
    
    /**
     * The MD2 message digest algorithm defined in RFC 1319.
     */
    MD2("MD2"),
    
    /**
     * The MD5 message digest algorithm defined in RFC 1321.
     */
    MD5("MD5"),
    
    /**
     * The SHA-1 hash algorithm defined in the FIPS PUB 180-2.
     */
    SHA_1("SHA-1"),
    
    /**
     * The SHA-224 hash algorithm defined in the FIPS PUB 180-3.
     * <p>
     * Present in Oracle Java 8.
     * </p>
     */
    SHA_224("SHA-224"),
    
    /**
     * The SHA-256 hash algorithm defined in the FIPS PUB 180-2.
     */
    SHA_256("SHA-256"),
    
    /**
     * The SHA-384 hash algorithm defined in the FIPS PUB 180-2.
     */
    SHA_384("SHA-384"),
    
    /**
     * The SHA-512 hash algorithm defined in the FIPS PUB 180-2.
     */
    SHA_512("SHA-512"),
    
    /**
     * The SHA-512 hash algorithm defined in the FIPS PUB 180-4.
     * <p>
     * Included starting in Oracle Java 9.
     * </p>
     */
    SHA_512_224("SHA-512/224"),
    
    /**
     * The SHA-512 hash algorithm defined in the FIPS PUB 180-4.
     * <p>
     * Included starting in Oracle Java 9.
     * </p>
     */
    SHA_512_256("SHA-512/256"),
    
    /**
     * The SHA3-224 hash algorithm defined in the FIPS PUB 202.
     * <p>
     * Included starting in Oracle Java 9.
     * </p>
     */
    SHA3_224("SHA3-224"),
    
    /**
     * The SHA3-256 hash algorithm defined in the FIPS PUB 202.
     * <p>
     * Included starting in Oracle Java 9.
     * </p>
     */
    SHA3_256("SHA3-256"),
    
    /**
     * The SHA3-384 hash algorithm defined in the FIPS PUB 202.
     * <p>
     * Included starting in Oracle Java 9.
     * </p>
     */
    SHA3_384("SHA3-384"),
    
    /**
     * The SHA3-512 hash algorithm defined in the FIPS PUB 202.
     * <p>
     * Included starting in Oracle Java 9.
     * </p>
     */
    SHA3_512("SHA3-512");
    
    private final String name;
    
    private MessageDigestAlgorithm(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }

}
