package fun.fengwk.commons.codec;

import org.junit.Test;

import java.util.Arrays;

/**
 * 
 * @author fengwk
 */
public class CodecUtilsTest {
    
    @Test
    public void test() {
        byte[] bytes = StringUtils.getBytesUtf8("hello world");
        
        String hexStr = CodecUtils.bytesToHexStr(bytes);
        System.out.println(hexStr);
        
        assert Arrays.equals(bytes, CodecUtils.hexStrToBytes(hexStr));
    }

}
