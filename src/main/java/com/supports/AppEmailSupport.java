package com.supports;

import com.models.AppEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class AppEmailSupport {

    private static final Logger LOGGER = Logger.getLogger("APP_EMAIL_SUPPORT");

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private SpringTemplateEngine springTemplateEngine;

    /**
     *  SEND EMAIL CONFIRM REGISTRATION
     */
    public void sendEmailByTemplate(AppEmail appEmail) {
        MimeMessage message = this.javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
            Context context = new Context();
            context.setVariables(appEmail.getVariables());
            String html = this.springTemplateEngine.process(appEmail.getTemplate(), context);

            mimeMessageHelper.setTo(appEmail.getTo());
            mimeMessageHelper.setSubject(appEmail.getSubject());
            mimeMessageHelper.setText(html, true);

            this.javaMailSender.send(message);
        }
        catch (MessagingException ex) {
            LOGGER.log(Level.WARNING, ex.getMessage());
        }
    }

    /**
     *  SEND SIMPLE MESSAGE
     */
    public void sendSimpleMessage(String to, String subject, String text) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(to);
            simpleMailMessage.setSubject(subject);
            simpleMailMessage.setText(text);
            this.javaMailSender.send(simpleMailMessage);
        }
        catch (MailException ex) {
            LOGGER.log(Level.WARNING, ex.getMessage());
        }
    }

    /**
     * SEND EMAIL WITH ATTACHMENT
     */
    public void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment) {
        try {
            MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(text);

            FileSystemResource fileSystemResource = new FileSystemResource(new File(pathToAttachment));
            mimeMessageHelper.addAttachment("Attachment File Name", fileSystemResource);

            this.javaMailSender.send(mimeMessage);
        }
        catch (MessagingException ex) {
            LOGGER.log(Level.WARNING, ex.getMessage());
        }
    }

}