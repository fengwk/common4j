package fun.fengwk.commons.codec;

/**
 * Base64编码解码器。
 *
 * @author fengwk
 */
public class Base64 {
    
    private static final byte EQ = '=';

    private static final char[] BASE_64 = { 
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 
            'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 
            'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 
            'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 
            'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 
            'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 
            'w', 'x', 'y', 'z', '0', '1', '2', '3', 
            '4', '5', '6', '7', '8', '9', '+', '/',
            };
    
    /**
     * 索引为编码的ASCII值，值为其在BASE_64中的索引，也就是BASE_64码值
     */
    private static final byte[] BASE_64_REVERSE;
    
    static {
        int maxIndex = -1;
        for (int i = 0; i < BASE_64.length; i++) {
            int c = BASE_64[i];
            if (c > maxIndex) {
                maxIndex = c;
            }
        }
        
        byte[] reverse = new byte[maxIndex + 1];
        for (int i = 0; i < BASE_64.length; i++) {
            reverse[BASE_64[i]] = (byte) i;
        }
        
        BASE_64_REVERSE = reverse;
    }

    /**
     * 将输入字节数组进行Base64编码。
     *
     * @param src
     * @return
     */
    public byte[] encode(byte[] src) {
        int len = src.length;
        if (len % 3 != 0) {
            len += 3 - (len % 3);
        }
        
        byte[] dest = new byte[len * 8 / 6];
        for (int i = 0, j = 0; j < dest.length; i += 3, j += 4) {
            byte t1 = src[i];
            byte t2 = i + 1 < src.length ? src[i + 1] : -1;
            byte t3 = i + 2 < src.length ? src[i + 2] : -1;
            
            if (t2 != -1 && t3 != -1) {
                dest[j] = (byte) BASE_64[((t1 >>> 2) & 0b111111)];
                dest[j + 1] = (byte) BASE_64[((t1 << 4) & 0b110000) | (t2 >>> 4) & 0b001111];
                dest[j + 2] = (byte) BASE_64[((t2 << 2) & 0b111100) | (t3 >>> 6) & 0b000011];
                dest[j + 3] = (byte) BASE_64[t3 & 0b111111];
            } else if (t2 == -1) {
                dest[j] = (byte) BASE_64[((t1 >>> 2) & 0b111111)];
                dest[j + 1] = (byte) BASE_64[(t1 << 4) & 0b110000];
                dest[j + 2] = EQ;
                dest[j + 3] = EQ;
            } else {
                dest[j] = (byte) BASE_64[((t1 >>> 2) & 0b111111)];
                dest[j + 1] = (byte) BASE_64[((t1 << 4) & 0b110000) | (t2 >>> 4) & 0b001111];
                dest[j + 2] = (byte) BASE_64[((t2 << 2) & 0b111100)];
                dest[j + 3] = EQ;
            }
        }
        
        return dest;
    }

    /**
     * 将输入的Base64编码数组进行解码。
     *
     * @param src
     * @return
     */
    public byte[] decode(byte[] src) {
        if (src.length % 4 != 0) {
            throw new IllegalArgumentException();
        }
        
        int len = src.length * 6 / 8;
        if (src.length - 2 >= 0 && src[src.length - 2] == EQ) {
            len -= 2;
        } else if (src.length - 1 >= 0 && src[src.length - 1] == EQ) {
            len -= 1;
        }
        
        byte[] dest = new byte[len];
        for (int i = 0, j = 0; i < src.length; i += 4, j += 3) {
            byte t1 = BASE_64_REVERSE[src[i]];
            byte t2 = BASE_64_REVERSE[src[i + 1]];
            byte t3 = src[i + 2] == EQ ? -1 : BASE_64_REVERSE[src[i + 2]];
            byte t4 = src[i + 3] == EQ ? -1 : BASE_64_REVERSE[src[i + 3]];
            
            if (t3 == -1) {
                dest[j] = (byte) (((t1 & 0b00111111) << 2) | ((t2 >>> 4) & 0b00000011));
            } else if (t4 == -1) {
                dest[j] = (byte) (((t1 & 0b00111111) << 2) | ((t2 >>> 4) & 0b00000011));
                dest[j + 1] = (byte) (((t2 & 0b00001111) << 4) | ((t3 >>> 2) & 0b00001111));
            } else {
                dest[j] = (byte) (((t1 & 0b00111111) << 2) | ((t2 >>> 4) & 0b00000011));
                dest[j + 1] = (byte) (((t2 & 0b00001111) << 4) | ((t3 >>> 2) & 0b00001111));
                dest[j + 2] = (byte) (((t3 & 0b00000011) << 6) | (t4 & 0b00111111));
            }
        }
        
        return dest;
    }
    
}
