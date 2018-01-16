package io.github.biezhi.email;

import org.apache.commons.mail.*;

/**
 * 发送带有附件的电子邮件
 *
 * @author biezhi
 * @date 2018/1/15
 */
public class SendWithAttachExample {

    public static void main(String[] args) throws EmailException {
        // 创建附件
        EmailAttachment attachment = new EmailAttachment();
        attachment.setPath("mypictures/john.jpg");
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        attachment.setDescription("Picture of John");
        attachment.setName("John");

        // 创建 Email Message
        MultiPartEmail email = new MultiPartEmail();
        email.setHostName("mail.myserver.com");
        email.addTo("jdoe@somewhere.org", "John Doe");
        email.setFrom("me@apache.org", "Me");
        email.setSubject("The picture");
        email.setMsg("Here is the picture you wanted");

        // 追加附件
        email.attach(attachment);

        // 发送
        email.send();
    }
}
