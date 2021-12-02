package fun.fengwk.commons.codec;

/**
 * 
 * @author fengwk
 */
public class RSAPrivateKey extends AbstractRSAKey {

    public RSAPrivateKey(java.security.interfaces.RSAPrivateKey privateKey) {
        super(privateKey);
    }
    
    public RSAPrivateKey(byte[] privateKey) {
        this(CipherUtils.generateRsaPrivateKey(privateKey));
    }

}
