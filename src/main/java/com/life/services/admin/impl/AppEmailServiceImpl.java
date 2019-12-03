package com.life.services.admin.impl;

import com.life.consts.EmailSubject;
import com.life.consts.EmailTemplate;
import com.life.consts.Variables;
import com.life.entities.admin.AppUser;
import com.life.entities.admin.AppUserToken;
import com.life.models.AppEmail;
import com.life.services.admin.AppEmailService;
import com.life.supports.AppEmailSupport;
import com.life.utils.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AppEmailServiceImpl implements AppEmailService {

    @Autowired
    private AppEmailSupport appEmailSupport;

    /**
     *  SEND EMAIL CONFIRM REGISTRATION
     */
    @Override
    public void sendConfirmRegistrationEmail(AppUser appUser) {
        AppEmail appEmail = new AppEmail();

        appEmail.setTo(appUser.getAppUserProfile().getEmailAddress());
        appEmail.setSubject(EmailSubject.CONFIRM_REGISTRATION);
        appEmail.setTemplate(EmailTemplate.CONFIRM_REGISTRATION);

        Map<String, Object> variables = new HashMap<>();
        variables.put(Variables.FIRST_NAME, appUser.getAppUserProfile().getFirstName());
        variables.put(Variables.LAST_NAME, appUser.getAppUserProfile().getLastName());
        AppUserToken appUserToken = new AppUserToken();
        variables.put(Variables.APP_USER_TOKEN, appUserToken.getAppUserTokenValue());
        variables.put(Variables.CONFIRM_REGISTRATION_URL, "http://localhost:8080/ConfirmRegistration/" + appUserToken.getAppUserTokenValue());
        variables.put(Variables.EXPIRATION_DATE, DateTimeUtils.dateToddMMyyyyHHmmss(appUserToken.getExpirationDate()));
        appEmail.setVariables(variables);

        this.appEmailSupport.sendEmailByTemplate(appEmail);
    }

}