package com.qugenx.sendmail.services;

import com.qugenx.sendmail.model.MailObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;

@Service("emailServiceFake")
@Profile("fake")
public class EmailServiceFake implements EmailService {

    private final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public void sendSimpleMessage(String[] to, String[] cc, String subject, String text) {

    }

    @Override
    public void sendSimpleMessageUsingTemplate(String[] to, String[] cc, String subject,
                                               String message, String template) {

    }

    @Override
    public void sendSimpleMessageUsingTemplateAndAttachment(MailObject mailObject, String pathToAttachment, String name, String templateName, String template) {

    }

    @Override
    public void sendMessageWithAttachment(String[] to, String[] cc, String subject, String text, String pathToAttachment, String name) {

    }

    @Override
    public void sendEmailUsingFreeMarkerTemplate(MailObject mailObject, String template) throws Exception {

    }


}
