package com.qugenx.sendmail.services;

import com.qugenx.sendmail.model.MailObject;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.lang.invoke.MethodHandles;

@Service("emailService")
@Profile({"dev", "qa"})
public class EmailServiceImpl implements EmailService {

    private final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Value("${app.sendmail.from}")
    private String from;

    @Autowired
    public JavaMailSender emailSender;

    @Autowired
    private Configuration freemarkerConfig;

    @Autowired
    private MailContentBuilder mailContentBuilder;

    @Override
    public void sendSimpleMessage(String[] to, String[] cc, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setCc(cc);
            message.setSubject(subject);
            message.setText(text);

            emailSender.send(message);
        } catch (MailException exception) {
            exception.printStackTrace();
        }

    }

    @Override
    public void sendSimpleMessageUsingTemplate(String[] to, String[] cc, String subject, String message, String template) {
        String content = mailContentBuilder.build(message, template);
        sendSimpleMessage(to, cc, subject, content);
    }

    @Override
    public void sendMessageWithAttachment(String[] to, String[] cc, String subject, String text, String pathToAttachment, String name) {

        MimeMessage msg = emailSender.createMimeMessage();

        // true = multipart message
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(msg, true, "UTF-8");
            helper.setTo(to);
            helper.setCc(cc);
            helper.setSubject(subject);
            // default = text/plain
            //helper.setText("Check attachment for image!");

            // true = text/html
            helper.setText(text, true);

            helper.addAttachment(name, new ClassPathResource(pathToAttachment));

            emailSender.send(msg);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


    //String recipient, String subject, String message,
    @Override
    public void sendSimpleMessageUsingTemplateAndAttachment(MailObject mailObject, String pathToAttachment, String name, String templateName, String template) {

        MimeMessage msg = emailSender.createMimeMessage();

        // true = multipart message
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(msg, true, "UTF-8");
            helper.setTo(mailObject.getMailTo());
            helper.setCc(mailObject.getMailCc());
            //helper.setFrom(from);
            helper.setSubject(mailObject.getMailSubject());

            String content = mailContentBuilder.build(mailObject.getMailContent(), template);
            helper.setText(content, true);

            // default = text/plain
            //helper.setText("Check attachment for image!");

            // true = text/html
            //helper.setText("<h1>Check attachment for image!</h1>", true);
            helper.addAttachment(name, new ClassPathResource(pathToAttachment));

            emailSender.send(msg);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

                /*String message = mailObject.getText();

        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("whataboutme.tn@gmail.com");
            messageHelper.setTo(mailObject.getTo());
            messageHelper.setSubject(mailObject.getSubject());

            String content = mailContentBuilder.build(message);
            messageHelper.setText(content, true);


            FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
            messageHelper.addAttachment("Invoice", file);
        };
        try {
            emailSender.send(messagePreparator);
        } catch (MailException e) {
            // runtime exception; compiler will not force you to handle it
        }*/

    }

    public void sendEmailUsingFreeMarkerTemplate(MailObject mailObject, String template) throws Exception {
        MimeMessage message = emailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");

        // Using a subfolder such as /templates here
        freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates");

        Template t = freemarkerConfig.getTemplate(template);
        String text = FreeMarkerTemplateUtils.processTemplateIntoString(t, mailObject.getModel());

        helper.setTo(mailObject.getMailTo());
        helper.setCc(mailObject.getMailCc());
        helper.setText(text, true);
        helper.setSubject(mailObject.getMailSubject());

        emailSender.send(message);
    }
}
