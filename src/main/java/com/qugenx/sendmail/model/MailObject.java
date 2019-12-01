package com.qugenx.sendmail.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MailObject {

    @NotNull
    private String[] mailTo;
    @NotNull
    private String[] mailCc;
    @NotNull
    private String[] mailBcc;

    private String mailFrom;
    //@Email
    @NotNull
    @Size(min = 1, message = "Please, set a subject")
    private String mailSubject;
    @NotNull
    @Size(min = 1, message = "Please, set a message")
    private String mailContent;

    private String contentType;

    private List< Object > attachments;

    private Map< String, Object > model;

    public MailObject() {
        contentType = "text/plain";
    }

    public String[] getMailTo() {
        return mailTo;
    }

    public void setMailTo(String[] mailTo) {
        this.mailTo = mailTo;
    }

    public String[] getMailCc() {
        return mailCc;
    }

    public void setMailCc(String[] mailCc) {
        this.mailCc = mailCc;
    }

    public String[] getMailBcc() {
        return mailBcc;
    }

    public void setMailBcc(String[] mailBcc) {
        this.mailBcc = mailBcc;
    }

    public String getMailFrom() {
        return mailFrom;
    }

    public void setMailFrom(String mailFrom) {
        this.mailFrom = mailFrom;
    }

    public String getMailSubject() {
        return mailSubject;
    }

    public void setMailSubject(String mailSubject) {
        this.mailSubject = mailSubject;
    }

    public String getMailContent() {
        return mailContent;
    }

    public void setMailContent(String mailContent) {
        this.mailContent = mailContent;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public List<Object> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Object> attachments) {
        this.attachments = attachments;
    }

    public Map<String, Object> getModel() {
        return model;
    }

    public void setModel(Map<String, Object> model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "MailObject{" +
                "mailTo=" + Arrays.toString(mailTo) +
                ", mailCc=" + Arrays.toString(mailCc) +
                ", mailBcc=" + Arrays.toString(mailBcc) +
                ", mailFrom='" + mailFrom + '\'' +
                ", mailSubject='" + mailSubject + '\'' +
                ", mailContent='" + mailContent + '\'' +
                ", contentType='" + contentType + '\'' +
                ", attachments=" + attachments +
                ", model=" + model +
                '}';
    }
}
