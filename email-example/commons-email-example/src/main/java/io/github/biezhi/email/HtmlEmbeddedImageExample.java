package io.github.biezhi.email;

import org.apache.commons.mail.*;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * 发送HTML格式的邮件与嵌入式图像
 *
 * @author biezhi
 * @date 2018/1/15
 */
public class HtmlEmbeddedImageExample {

    public static void main(String[] args) throws EmailException, MalformedURLException {
        // 加载邮件模板
        String htmlEmailTemplate = ".... <img src=\"http://www.apache.org/images/feather.gif\"> ....";

        // 定义URL资源
        URL url = new URL("http://www.apache.org");

        // 创建 Email Message
        ImageHtmlEmail email = new ImageHtmlEmail();
        email.setDataSourceResolver(new DataSourceUrlResolver(url));
        email.setHostName("mail.myserver.com");
        email.addTo("jdoe@somewhere.org", "John Doe");
        email.setFrom("me@apache.org", "Me");
        email.setSubject("Test email with inline image");

        // 设置 html 内容
        email.setHtmlMsg(htmlEmailTemplate);

        // 设置替代消息
        email.setTextMsg("Your email client does not support HTML messages");

        // 发送
        email.send();
    }
}
