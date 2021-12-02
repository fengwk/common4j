package fun.fengwk.commons.codec;

/**
 * 
 * @author fengwk
 */
public class CodecUtils {

    private static final char[] HEX = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    
    private CodecUtils() {}
    
    /**
     * 将bytes转换为16进制字符串。
     * 
     * @param bytes
     * @return
     */
    public static String bytesToHexStr(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(HEX[(bytes[i] >> 4) & 0x0f]);
            sb.append(HEX[bytes[i] & 0x0f]);
        }
        return sb.toString();
    }
    
    /**
     * 16进制字符串转为bytes数组。
     * 
     * @param hex
     * @return
     */
    public static byte[] hexStrToBytes(String hex) {
        if (hex.length() % 2 != 0) {
            throw new IllegalArgumentException();
        }
        
        byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; i < hex.length(); i += 2) {
            int hi = Character.digit(hex.charAt(i), 16);
            int lo = Character.digit(hex.charAt(i + 1), 16);
            bytes[i / 2] = (byte) ((hi << 4) | lo);
        }
        return bytes;
    }
    
}
