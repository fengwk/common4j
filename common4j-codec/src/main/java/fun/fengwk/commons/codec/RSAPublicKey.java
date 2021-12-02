package fun.fengwk.commons.codec;

/**
 * 
 * @author fengwk
 */
public class RSAPublicKey extends AbstractRSAKey {
    
    public RSAPublicKey(java.security.interfaces.RSAPublicKey publicKey) {
        super(publicKey);
    }

    public RSAPublicKey(byte[] publicKey) {
        this(CipherUtils.generateRsaPublicKey(publicKey));
    }

}
