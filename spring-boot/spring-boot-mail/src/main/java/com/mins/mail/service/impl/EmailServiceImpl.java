package com.mins.mail.service.impl;

import com.mins.mail.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Map;

/**
 * @author <a href="http://minsming.com">小铭</a>
 * Date: 2018/4/25
 * No struggle, talent how to match the willfulness.
 * Description: 逻辑层实现类
 */
@Slf4j
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public void sendSimpleMessage(String[] to, String subject, String text, boolean html) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, html);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        mailSender.send(message);
        log.info("邮件发送完成");
    }

    @Override
    public void sendMessageWithAttachment(String[] to, String subject, String text, Map<String, String> pathToAttachments, boolean html) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, html);
            for (String filename : pathToAttachments.keySet()) {
                FileSystemResource fileSystemResource = new FileSystemResource(new File(pathToAttachments.get(filename)));
                helper.addAttachment(filename, fileSystemResource);
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        mailSender.send(message);
        log.info("邮件发送完成");
    }
}
