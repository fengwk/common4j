# Internationalization

该模块基于`ResourceBundle`与`OGNL`提供简单易用的国际化支持。

# Usage

直接使用`StringManager`：

1. 在resources下新建`name_language_city.properties`文件，例如`message_zh_CN.properties`。

2. 在`message_zh_CN.properties`中添加如下内容：

   ```properties
   fun.fengwk.commons.i18n.StringManagerFactoryTest.message=你好，${name}
   ```

3. 编写示例代码：

   ```java
   public class Test {
   	
   	public static void main(String[] args) {
           ResourceBundleLoader loader = new PropertiesResourceBundleLoader();
   	    ResourceBundle resourceBundle = loader.load("message", Locale.CHINA);
   	    StringManagerFactory stringManagerFactory = new StringManagerFactory(resourceBundle);
   	    StringManager stringManager = stringManagerFactory.getStringManager(Test.class);
   
   	    Map<String, Object> ctx = new ConcurrentHashMap<>();
   	    ctx.put("name", "冯先生");
   	    String str = stringManager.getString("message", ctx);
   	    System.out.println(str);
   	}
   	
   }
   ```

使用`StringManager`代理：

1. 前两步与直接使用`StringManager`一致。

2. 编写代理接口：

   ```java
   public interface StringManagerProxy {
       String message(@Name("name") String name);
   }
   ```

3. 编写示例代码：

   ```java
   public class Test {
   	
   	public static void main(String[] args) {
           ResourceBundleLoader loader = new PropertiesResourceBundleLoader();
           ResourceBundle resourceBundle = loader.load("message", Locale.CHINA);
           StringManagerFactory stringManagerFactory = new StringManagerFactory(resourceBundle);
           StringManagerProxy proxy = stringManagerFactory.getStringManagerProxy(StringManagerProxy.class, Test.class);
           String str = proxy.message("冯先生");
           System.out.println(str);
   	}
   	
   }
   ```

# References

- [ResourceBundle](https://zetcode.com/java/resourcebundle/)
- [OGNL语法手册](https://commons.apache.org/proper/commons-ognl/language-guide.html)