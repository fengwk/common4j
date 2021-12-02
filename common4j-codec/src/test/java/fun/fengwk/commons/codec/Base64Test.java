package fun.fengwk.commons.codec;

import org.junit.Test;

import java.util.Arrays;

/**
 * 
 * @author fengwk
 */
public class Base64Test {

    @Test
    public void test() {
        byte[] src = StringUtils.getBytesUtf8("你好，fengwk。");
        Base64 base64 = new Base64();
        byte[] myBase64Encode = base64.encode(src);
        byte[] myBase64Decode = base64.decode(myBase64Encode);
        
        assert Arrays.equals(java.util.Base64.getEncoder().encode(src), myBase64Encode);
        assert Arrays.equals(src, myBase64Decode);
    }
    
}
