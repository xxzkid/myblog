package org.xxz.myblog.util;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Slf4j
@Component
@ConfigurationProperties(prefix = "mail")
public class MailUtil {
    
    @Autowired
    private JavaMailSender sender;
    
    @Getter @Setter
    private String from;

    /**
     * 发送文本邮件
     * @param to 接受人
     * @param subject 主题
     * @param text 文本
     */
    public void sendText(String to, String subject, String text) throws MessagingException {
        log.info("===>to:{}, subject:{}, text:{}", to, subject, text);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        sender.send(message);
        log.info("===>send text mail finish");
    }
    
    /**
     * 发送html邮件
     * @param to
     * @param subject
     * @param text
     * @throws MessagingException
     */
    public void sendHtml(String to, String subject, String text) throws MessagingException {
    	log.info("===>to:{}, subject:{}, text:{}", to, subject, text);
    	MimeMessage message = sender.createMimeMessage();
    	MimeMessageHelper helper = new MimeMessageHelper(message, true);
    	helper.setTo(to);
    	helper.setFrom(from);
    	helper.setSubject(subject);
    	helper.setText(text, true);
    	sender.send(message);
    	log.info("===>send html mail finish");
    }
    
    /**
     * 发送附件邮件
     * @param to
     * @param subject
     * @param text
     * @param filePath
     * @throws MessagingException
     */
    public void sendAttachments(String to, String subject, String text, String filePath) throws MessagingException {
    	log.info("===>to:{}, subject:{}, text:{}, filePath:{}", to, subject, text, filePath);
    	MimeMessage message = sender.createMimeMessage();
    	MimeMessageHelper helper = new MimeMessageHelper(message, true);
    	helper.setTo(to);
    	helper.setFrom(from);
    	helper.setSubject(subject);
    	helper.setText(text, true);
    	FileSystemResource file = new FileSystemResource(new File(filePath));
    	String attachmentFilename = filePath.substring(filePath.lastIndexOf(File.separator) + 1);
    	helper.addAttachment(attachmentFilename, file);
    	sender.send(message);
    	log.info("===>send attachments mail finish");
    }
    
    /**
     * 发送嵌入资源(一般是图片)邮件
     * @param to
     * @param subject
     * @param text 带图片的邮件<img src=\"cid:resId1\">
     * @param resPath 文件路径
     * @param resId 静态资源id
     * @throws MessagingException
     */
    public void sendInlineResource(String to, String subject, String text, String resPath, String resId) throws MessagingException {
    	log.info("===>to:{}, subject:{}, text:{}, resPath:{}, resId:{}", to, subject, text, resPath, resId);
    	MimeMessage message = sender.createMimeMessage();
    	MimeMessageHelper helper = new MimeMessageHelper(message, true);
    	helper.setTo(to);
    	helper.setFrom(from);
    	helper.setSubject(subject);
    	helper.setText(text, true);
    	
    	FileSystemResource resource = new FileSystemResource(new File(resPath));
    	helper.addInline(resId, resource);
    	sender.send(message);
    	log.info("===>send inline resource mail finish");
    }
    
}
