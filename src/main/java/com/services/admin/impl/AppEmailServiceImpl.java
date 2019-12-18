package com.services.admin.impl;

import com.consts.AppURL;
import com.consts.EmailSubject;
import com.consts.EmailTemplate;
import com.consts.Variables;
import com.entities.admin.AppUser;
import com.entities.admin.AppUserToken;
import com.models.AppEmail;
import com.services.admin.AppEmailService;
import com.supports.AppEmailSupport;
import com.utils.DateTimeUtils;
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
    public void sendConfirmRegistrationEmail(AppUser appUser, AppUserToken appUserToken) {
        AppEmail appEmail = new AppEmail();

        appEmail.setTo(appUser.getAppUserProfile().getEmailAddress());
        appEmail.setSubject(EmailSubject.CONFIRM_REGISTRATION);
        appEmail.setTemplate(EmailTemplate.CONFIRM_REGISTRATION);

        Map<String, Object> variables = new HashMap<>();
        variables.put(Variables.FIRST_NAME, appUser.getAppUserProfile().getFirstName());
        variables.put(Variables.LAST_NAME, appUser.getAppUserProfile().getLastName());
        variables.put(Variables.CONFIRM_REGISTRATION_URL, AppURL.CONFIRM_REGISTRATION + "/" + appUserToken.getAppUserTokenValue());
        variables.put(Variables.EXPIRATION_DATE, DateTimeUtils.dateToddMMyyyyHHmmss(appUserToken.getExpirationDate()));
        appEmail.setVariables(variables);

        this.appEmailSupport.sendEmailByTemplate(appEmail);
    }

    /**
     *  SEND EMAIL UNLOCK APP USER
     */
    @Override
    public void sendEmailUnlockAppUser(AppUser appUser, AppUserToken appUserToken) {
        AppEmail appEmail = new AppEmail();

        appEmail.setTo(appUser.getAppUserProfile().getEmailAddress());
        appEmail.setSubject(EmailSubject.UNLOCK_APP_USER);
        appEmail.setTemplate(EmailTemplate.UNLOCK_APP_USER);

        Map<String, Object> variables = new HashMap<>();
        variables.put(Variables.FIRST_NAME, appUser.getAppUserProfile().getFirstName());
        variables.put(Variables.LAST_NAME, appUser.getAppUserProfile().getLastName());
        variables.put(Variables.UNLOCK_APP_USER_URL, AppURL.UNLOCK_APP_USER + "/" + appUserToken.getAppUserTokenValue());
        variables.put(Variables.EXPIRATION_DATE, DateTimeUtils.dateToddMMyyyyHHmmss(appUserToken.getExpirationDate()));
        appEmail.setVariables(variables);

        this.appEmailSupport.sendEmailByTemplate(appEmail);
    }

    /**
     *  SEND EMAIL FORGOT PASSWORD
     */
    @Override
    public void sendEmailForgotPassword(AppUser appUser, String password) {
        AppEmail appEmail = new AppEmail();

        appEmail.setTo(appUser.getAppUserProfile().getEmailAddress());
        appEmail.setSubject(EmailSubject.FORGOT_PASSWORD);
        appEmail.setTemplate(EmailTemplate.FORGOT_PASSWORD);

        Map<String, Object> variables = new HashMap<>();
        variables.put(Variables.FIRST_NAME, appUser.getAppUserProfile().getFirstName());
        variables.put(Variables.LAST_NAME, appUser.getAppUserProfile().getLastName());
        variables.put(Variables.PASSWORD, password);
        appEmail.setVariables(variables);

        this.appEmailSupport.sendEmailByTemplate(appEmail);
    }

}