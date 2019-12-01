package com.qugenx.sendmail.services.template;

import java.util.HashMap;
import java.util.Map;

public enum EmailTemplateCode {

    T1(101, "Email d'inscription'", "inscription-mail.ftl"),
    T2(101, "Email de Confirmation", "confirm-mail.ftl"),
    T3(101, "Email de Réinitialisation", "reset-mail.ftl");

    private static final Map<Integer, EmailTemplateCode> BL_CODE = new HashMap<>();
    private static final Map<String, EmailTemplateCode> BL_LABEL = new HashMap<>();
    private static final Map<String, EmailTemplateCode> BL_TEMPLATE = new HashMap<>();

    static {
        for (EmailTemplateCode e : values()) {
            BL_CODE.put(e.code, e);
            BL_LABEL.put(e.label, e);
            BL_TEMPLATE.put(e.template, e);
        }
    }

    public final int code;
    public final String label;
    public final String template;

    private EmailTemplateCode(int code, String label, String template) {
        this.code = code;
        this.label = label;
        this.template = template;
    }

    public static EmailTemplateCode valueOfCode(int number) {
        return BL_CODE.get(number);
    }


    public static EmailTemplateCode valueOfLabel(String label) {
        for (EmailTemplateCode e : values()) {
            if (e.label.equals(label)) {
                return e;
            }
        }
        return null;
    }

    public static EmailTemplateCode valueOfTemplate(String template) {
        for (EmailTemplateCode e : values()) {
            if (e.label.equals(template)) {
                return e;
            }
        }
        return null;
    }
}

