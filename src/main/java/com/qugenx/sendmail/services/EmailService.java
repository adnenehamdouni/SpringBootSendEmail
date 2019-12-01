package com.qugenx.sendmail.services;

import com.qugenx.sendmail.model.MailObject;

public interface EmailService {

    void sendSimpleMessage(final String[] to,
                           final String[] cc, String subject,
                           final String text);
    void sendSimpleMessageUsingTemplate(final String[] to,
                                        final String[] cc,
                                        final String subject,
                                        final String message,
                                        final String template);

    void sendSimpleMessageUsingTemplateAndAttachment(final MailObject mailObject,
                                                     final String pathToAttachment,
                                                     final String name,
                                                     final String templateName,
                                                     final String template);

    void sendMessageWithAttachment(final String[] to, final String[] cc,
                                   final String subject,
                                   final String text,
                                   final String pathToAttachment, String name);

    void sendEmailUsingFreeMarkerTemplate(final MailObject mailObject,
                                          final String template) throws Exception;

}
