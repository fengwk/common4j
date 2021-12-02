package fun.fengwk.commons.codec;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * @author fengwk
 */
public class MessageDigestUtils {
    
    private MessageDigestUtils() {}
    
    public static MessageDigest get(MessageDigestAlgorithm algorithm) {
        try {
            return MessageDigest.getInstance(algorithm.getName());
        } catch (final NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
