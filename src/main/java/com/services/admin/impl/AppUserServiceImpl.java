package com.services.admin.impl;

import com.entities.admin.AppUser;
import com.entities.admin.AppUserRole;
import com.entities.admin.AppUserToken;
import com.entities.user.AppUserProfile;
import com.repositories.admin.AppUserRepository;
import com.services.admin.AppEmailService;
import com.services.admin.AppUserRoleService;
import com.services.admin.AppUserService;
import com.services.admin.AppUserTokenService;
import com.services.user.AppUserProfileService;
import com.supports.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class AppUserServiceImpl implements AppUserService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private AppUserProfileService appUserProfileService;

    @Autowired
    private AppUserRoleService appUserRoleService;

    @Autowired
    private AppUserTokenService appUserTokenService;

    @Autowired
    private AppEmailService appEmailService;

    /**
     *   LOAD USER BY USERNAME
     */
    @Transactional
    @Override
    public AppUser findAppUserByAppUsername(String appUsername) {
        return this.appUserRepository.findAppUserByAppUsername(appUsername);
    }

    /**
     *
     */
    @Transactional
    @Override
    public String signInAccount(AppUser appUser) {
        return this.appUserRepository.signInAccount(appUser);
    }

    /**
     *  CHECK USERNAME EXISTS
     */
    @Transactional
    @Override
    public boolean appUsernameExists(String appUsername) {
        return this.appUserRepository.appUsernameExists(appUsername);
    }

    /**
     *  INSERT APP USER
     */
    @Transactional
    @Override
    public void createAppUser(AppUser appUser) {
        AppUserProfile appUserProfile = appUser.getAppUserProfile();
        Set<AppUserRole> appUserRoleSet = appUser.getAppUserRoleSet();

        /** INSERT APP_USER **/
        appUser = this.appUserRepository.createAppUser(appUser);

        /** INSERT APP_USER_PROFILE **/
        appUserProfile.setAppUser(appUser);
        appUserProfile = this.appUserProfileService.createAppUserProfile(appUserProfile);

        /** INSERT APP_USER_ROLE **/
        this.appUserRoleService.createAppUserRoleList(appUserRoleSet, appUser);

        /** INSERT CONFIRMATION TOKEN **/
        AppUserToken appUserToken = this.appUserTokenService.createConfirmationToken(appUser);

        /** SEND EMAIL CONFIRMATION REGISTRATION **/
        appUser.setAppUserProfile(appUserProfile);
        this.appEmailService.sendConfirmRegistrationEmail(appUser, appUserToken);
    }

    /**
     *  RESEND CONFIRMATION TOKEN
     */
    @Transactional
    @Override
    public void resendConfirmationToken(AppUser appUser) {
        /** REMAKE CONFIRMATION TOKE FOR APP USER **/
        AppUserToken appUserToken = this.appUserTokenService.remakeConfirmationToken(appUser);
        /** SEND EMAIL CONFIRMATION REGISTRATION **/
        this.appEmailService.sendConfirmRegistrationEmail(appUserToken.getAppUser(), appUserToken);
    }

    /**
     *  SEND EMAIL UNLOCK APP USER
     */
    @Transactional
    @Override
    public void sendEmailUnlockAppUser(AppUser appUser) {
        appUser = this.appUserRepository.findAppUserByAppUsername(appUser.getAppUsername());
        /** INSERT UNLOCK APP USER TOKEN **/
        AppUserToken appUserToken = this.appUserTokenService.createUnlockAppUserToken(appUser);
        /** SEND EMAIL UNLOCK APP USER **/
        this.appEmailService.sendEmailUnlockAppUser(appUser, appUserToken);
    }

    /**
     *  CONFIRM REGISTRATION APP USER
     */
    @Transactional
    @Override
    public String confirmRegistration(String appUserTokenValue) {
        return this.appUserRepository.confirmRegistration(appUserTokenValue);
    }

    /**
     *  SEND EMAIL FORGOT PASSWORD
     */
    @Transactional
    @Override
    public void sendEmailForgotPassword(AppUser appUser) {
        appUser = this.appUserRepository.findAppUserByAppUsername(appUser.getAppUsername());
        /** GENERATED NEW PASSWORD **/
        String password = this.passwordEncoder.generatedPassword();
        appUser.setEncryptedPassword(this.passwordEncoder.encryptPassword(password));
        /** UPDATE NEW PASSWORD APP USER **/
        appUser = this.appUserRepository.updateAppUser(appUser);
        /** SEND EMAIL FORGOT PASSWORD **/
        this.appEmailService.sendEmailForgotPassword(appUser, password);
    }

    /**
     *  UNLOCK APP USER
     */
    @Transactional
    @Override
    public String unlockAppUser(String appUserTokenValue) {
        return this.appUserRepository.unlockAppUser(appUserTokenValue);
    }

    /**
     *  CHECK NEW PASSWORD EQUALS CURRENT PASSWORD
     *  EQUALS RETURN TRUE
     *  NOT EQUALS RETURN FALSE
     */
    @Override
    public boolean passwordUsing(AppUser appUser, String password) {
        return this.passwordEncoder.matches(password, appUser.getEncryptedPassword());
    }

    /**
     *  CHANGE PASSWORD
     */
    @Transactional
    @Override
    public void changePassword(AppUser appUser, String password) {
        this.appUserRepository.changePassword(appUser, password);
    }

}