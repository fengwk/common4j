package fun.fengwk.commons.codec;

import java.io.PrintStream;
import java.security.Provider;
import java.security.Security;
import java.util.Set;

/**
 * 
 * @author fengwk
 */
public class CipherTransformation {
    
    private static final String SEPARATOR = "/";
    private static final String CIPHER = "Cipher";
    
    private final CipherAlgorithm algorithm;
    private final CipherMode mode;
    private final CipherPadding padding;
    
    public CipherTransformation(CipherAlgorithm algorithm) {
        this(algorithm, null, null);
    }
    
    public CipherTransformation(CipherAlgorithm algorithm, CipherMode mode, CipherPadding padding) {
        this.algorithm = algorithm;
        this.mode = mode;
        this.padding = padding;
    }
    
    public String getAlgorithm() {
        return algorithm.getName();
    }

    public String getTransformation() {
        if (algorithm != null && mode != null && padding != null) {
            return algorithm.getName() + SEPARATOR + mode.getName() + SEPARATOR + padding.getName();
        } else {
            return algorithm.getName();
        }
    }
    
    public static void printAllProviders() {
        printAllProviders(System.out);
    }
    
    public static void printAllProviders(PrintStream printer) {
        Provider[] providers = Security.getProviders();
        if (null != providers) {
            for (Provider provider : providers) {
                Set<Provider.Service> services = provider.getServices();
                for (Provider.Service service : services) {
                    if (CIPHER.equals(service.getType())) {
                        printer.println(String.format("provider:%s,type:%s,algorithm:%s", service.getProvider(), service.getType(), service.getAlgorithm()));
                    }
                }
            }
        }
    }

}
