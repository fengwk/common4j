package fun.fengwk.commons.util.http;

import fun.fengwk.commons.util.StringUtils;

import java.util.Base64;

/**
 * HTTP Basic Auth
 * {@code
 * 1. GET url HTTP/1.1
 * 2. HTTP/1.1 401 Unauthorized
 *    WWW-Authenticate:Basic realm="Access to the staging site"
 * 3. 填写用户名密码
 * 4. GET url HTTP/1.1
 *    Authentication:Basic base64Encode(username:password)
 * 5. HTTP/1.1 200 OK
 * }
 * 
 * @see <a href="https://www.jianshu.com/p/4cd42f7359f4">basic auth</a>
 * @author fengwk
 */
public class BasicAuth {
    
    private static final String PREFIX = "Basic ";
    private static final String SEPARATOR = ":";
    
    private final String username;
    private final String password;
    
    public BasicAuth(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public String encode() {
        return PREFIX + Base64.getEncoder().encodeToString(
                StringUtils.getBytesUtf8((username + SEPARATOR + password)));
    }

}
