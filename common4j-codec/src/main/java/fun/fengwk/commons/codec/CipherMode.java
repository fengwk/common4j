package fun.fengwk.commons.codec;

/**
 * 工作模式。
 * 
 * @author fengwk
 */
public enum CipherMode {

    /**
     * 电子密码本(Electronic CodeBook, ECB)，用相同的密钥分别对明文分组独立加密。
     */
    ECB("ECB"),
    
    /**
     * 密码分组链接(Cipher Block Chaining, CBC)，加密算法的输入是上一个密文组合下一个明文组的异或。
     */
    CBC("CBC"),
    
    /**
     * 密文反馈(Cipher FeedBack, CFB)，一次处理s位，上一块密文作为加密算法的输入，产生的伪随机数输出与明文异或作为下一单元的密文。
     */
    CFB("CFB"),
    
    /**
     * 输出反馈(Output FeedBack, OFB)，与CFB类似，只是加密算法的输入是上一次加密的输出，并且使用整个分组。
     */
    OFB("OFB"),
    
    /**
     * 计数器(Counter, CTR)，每个明文分组都与一个经过加密的计数器相异或。对每个后续分组计数器递增。
     */
    CTR("CTR");
    
    private final String name;
    
    private CipherMode(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
}
