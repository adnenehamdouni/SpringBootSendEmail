package com.qugenx.sendmail.email;

import com.qugenx.sendmail.config.TestEmailServiceContextConfig;
import com.qugenx.sendmail.services.MailContentBuilder;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.lang.invoke.MethodHandles;

@SpringBootTest(classes = TestEmailServiceContextConfig.class)
@EnableAutoConfiguration
@ActiveProfiles("qa")
@RunWith(SpringRunner.class)
public class EmailServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private MailContentBuilder mailContentBuilder;

    @Before
    public void setUp() {
    }

    @Test
    //@Ignore
    public void testSendEmail() {
        // given

        // when

        // then

        sendEmail();

    }

    @Test
    //@Ignore
    public void testSendEmailWithAttachment() throws IOException, MessagingException {
        // given

        // when

        // then

        sendEmailWithAttachment();

    }

    @Test
    //@Ignore
    public void testSendEmailUsingTemplate() throws IOException, MessagingException {
        // given

        // when

        // then

        sendEmailUsingTemplateWithAttachment();

    }


    void sendEmail() {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("adnene.hamdouni@gmail.com", "adnene.hamdouni@gmail.com");

        msg.setSubject("Testing from Spring Boot");
        msg.setText("Hello World \n Spring Boot Email");

        javaMailSender.send(msg);

    }


    private void sendEmailUsingTemplateWithAttachment() throws MessagingException, IOException {

        MimeMessage msg = javaMailSender.createMimeMessage();

        // true = multipart message
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setTo("adnene.hamdouni@gmail.com");
        helper.setFrom("whataboutme.tn@gmail.com");
        helper.setSubject("Testing from Spring Boot");

        String message = "Check template with red color formessage with attachment for image!";
        String content = mailContentBuilder.build(message, "mailTemplate.html");
        helper.setText(content, true);

        // default = text/plain
        //helper.setText("Check attachment for image!");

        // true = text/html
        //helper.setText("<h1>Check attachment for image!</h1>", true);
        helper.addAttachment("my_photo.jpg", new ClassPathResource("images/facebook.jpg"));

        javaMailSender.send(msg);


    }

    void sendEmailWithAttachment() throws MessagingException, IOException {

        MimeMessage msg = javaMailSender.createMimeMessage();

        // true = multipart message
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setTo("adnene.hamdouni@gmail.com");

        helper.setSubject("Testing from Spring Boot");

        // default = text/plain
        //helper.setText("Check attachment for image!");

        // true = text/html
        helper.setText("<h1>Check attachment for image!</h1>", true);

        helper.addAttachment("my_photo.jpg", new ClassPathResource("images/facebook.jpg"));

        javaMailSender.send(msg);

    }


}
