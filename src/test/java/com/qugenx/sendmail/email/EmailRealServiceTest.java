package com.qugenx.sendmail.email;

import com.qugenx.sendmail.config.TestEmailServiceContextConfig;
import com.qugenx.sendmail.model.MailObject;
import com.qugenx.sendmail.services.EmailService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.HashMap;
import java.util.Map;

//@SpringBootTest(classes = TestEmailServiceContextConfig.class)
//@EnableAutoConfiguration

@SpringBootTest
@ActiveProfiles("qa")
@RunWith(SpringRunner.class)
public class EmailRealServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    //@Spy
    private EmailService emailService;

    @Before
    public void setUp() {
    }

    @Test
    //@Ignore
    public void testSendEmail() {
        // given
        String[] to = new String[]{"adnene.hamdouni@gmail.com", "adnen.hamdouni@gmail.com"};
        String[] cc = new String[]{"adnene.hamdouni@gmail.com", "adnen.hamdouni@gmail.com"};

        // when

        // then
        emailService.sendSimpleMessage(to,
                cc,
                "Testing from Spring Boot",
                "Hello World \n Spring Boot Email");

    }

    @Test
    //@Ignore
    public void testSendSimpleMessageUsingTemplate() {
        // given
        String[] to = new String[]{"adnene.hamdouni@gmail.com", "adnen.hamdouni@gmail.com"};
        String[] cc = new String[]{"adnene.hamdouni@gmail.com", "adnen.hamdouni@gmail.com"};

        // when

        // then
        emailService.sendSimpleMessageUsingTemplate(to,
                cc,
                "Testing from Spring Boot",
                "Hello World \n Spring Boot Email",
                "mailTemplateSimple.html");


        //assertEquals(1, message.getTo().length);
        //assertEquals(email, message.getTo()[0]);
        //assertEquals(subject, message.getSubject());
        //assertEquals(expected, message.getText());

    }

    @Test
    //@Ignore
    public void testSendSimpleMessageUsingFreeMarkerTemplate() throws Exception {
        // given
        // given
        MailObject mailObject = new MailObject();
        mailObject.setMailTo(new String[]{"adnene.hamdouni@gmail.com"});
        mailObject.setMailCc(new String[]{"adnen.hamdouni@gmail.com"});
        mailObject.setMailSubject("Spring Boot - Email with FreeMarker template");
        mailObject.setMailContent("Send email using FreeMarker :)!");

        Map<String, Object> model = new HashMap<>();
        model.put("firstName", "Adnene");
        model.put("lastName", "Hamdouni");
        model.put("location", "Paris");
        model.put("signature", "www.qugenx.com");
        mailObject.setModel(model);
        // when

        // then
        emailService.sendEmailUsingFreeMarkerTemplate(mailObject,
                "email/mailTemplateFreeMarker.ftl");

    }



    @Test
    //@Ignore
    public void testSendEmailWithAttachment() throws IOException, MessagingException {
        // given
        String[] to = new String[]{"adnene.hamdouni@gmail.com", "adnen.hamdouni@gmail.com"};
        String[] cc = new String[]{"adnene.hamdouni@gmail.com", "adnen.hamdouni@gmail.com"};

        // when

        // then
        emailService.sendMessageWithAttachment( to, cc,
                "Testing from Spring Boot",
                "<h1>Check attachment for image!</h1>",
                "images/facebook.jpg",
                "my_photo.jpg");

    }

    @Test
    //@Ignore
    public void testSendSimpleMessageUsingTemplateAndAttachment() throws IOException, MessagingException {
        // given
        MailObject mailObject = new MailObject();
        mailObject.setMailTo(new String[]{"adnene.hamdouni@gmail.com"});
        mailObject.setMailCc(new String[]{"adnen.hamdouni@gmail.com"});
        mailObject.setMailSubject("Testing from Spring Boot");
        mailObject.setMailContent("Check template with red color formessage with attachment for image!");

        Map<String, Object> model = new HashMap<>();
        model.put("firstName", "Adnene");
        model.put("lastName", "Hamdouni");
        model.put("location", "Paris");
        model.put("signature", "www.qugenx.com");
        mailObject.setModel(model);
        // when

        // then
        emailService.sendSimpleMessageUsingTemplateAndAttachment(mailObject,
                "images/facebook.jpg",
                "my_photo.jpg",
                null,
                "mailTemplate.html");

    }


}
