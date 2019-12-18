package com.models;

import java.io.Serializable;
import java.util.Map;

public class AppEmail implements Serializable {

    private static final long serialVersionUID = 1L;

    private String to;
    private String subject;
    private Map<String, Object> variables;
    private String template;

    public AppEmail() {}

    public AppEmail(String to, String subject, Map<String, Object> variables, String template) {
        this.to = to;
        this.subject = subject;
        this.variables = variables;
        this.template = template;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Map<String, Object> getVariables() {
        return variables;
    }

    public void setVariables(Map<String, Object> variables) {
        this.variables = variables;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

}