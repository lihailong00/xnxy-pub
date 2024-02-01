package com.lee.xnxy.util;

import com.lee.xnxy.model.dao.paper.MyMail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author 晓龙coding
 */
@Component
@Slf4j
public class MyMailSender {
    @Value("${spring.mail.username}")
    private String from;
    @Resource
    private JavaMailSender mailSender;

    /**
     * 发送邮件
     * @param mail 邮件对象
     */
    @Async("taskExecutor")
    public void send(MyMail mail) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            // 这里设置为true才能发送附件
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(mail.getReceiver());
            helper.setSubject(mail.getSubject());
            //第二个参数为true表示邮件正文是html格式的，默认是false
            helper.setText(mail.getContent(), mail.isHtml());
            if (mail.getCc() != null) {
                helper.setCc(mail.getCc());
            }
            if (mail.getBcc() != null) {
                helper.setBcc(mail.getBcc());
            }
            // 添加附件
            if (mail.getAttachment() != null) {
                helper.addAttachment(mail.getAttachFileName(), mail.getAttachment());
            }
            mailSender.send(helper.getMimeMessage());
        } catch (MessagingException e) {
            log.info("邮件发送失败~");
        }
    }
}