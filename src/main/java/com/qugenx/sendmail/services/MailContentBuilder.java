package com.qugenx.sendmail.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailContentBuilder {

    private TemplateEngine templateEngine;

    @Autowired
    public MailContentBuilder(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public String build(String message, String template) {
        Context context = new Context();
        context.setVariable("message", message);
        //String content = EmailTemplateCode.valueOf("templateName").template);
        return templateEngine.process(template, context);
    }

}
