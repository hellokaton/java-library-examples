package io.github.biezhi.email;

import org.apache.commons.mail.*;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * 发送HTML格式的邮件
 *
 * @author biezhi
 * @date 2018/1/15
 */
public class HtmlMailExample {

    public static void main(String[] args) throws EmailException, MalformedURLException {
        // 创建 Email Message
        HtmlEmail email = new HtmlEmail();
        email.setHostName("mail.myserver.com");
        email.addTo("jdoe@somewhere.org", "John Doe");
        email.setFrom("me@apache.org", "Me");
        email.setSubject("Test email with inline image");

        // 嵌入图片
        URL url = new URL("http://www.apache.org/images/asf_logo_wide.gif");
        String cid = email.embed(url, "Apache logo");

        // 发送 HTML 内容
        email.setHtmlMsg("<html>The apache logo - <img src=\"cid:"+cid+"\"></html>");

        // 设置替代消息
        email.setTextMsg("Your email client does not support HTML messages");

        // 发送
        email.send();
    }
}
