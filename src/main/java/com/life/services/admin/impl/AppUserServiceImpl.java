package com.life.services.admin.impl;

import com.life.entities.admin.AppUser;
import com.life.entities.admin.AppUserRole;
import com.life.entities.admin.AppUserToken;
import com.life.entities.user.AppUserProfile;
import com.life.repositories.admin.AppUserRepository;
import com.life.services.admin.AppEmailService;
import com.life.services.admin.AppUserRoleService;
import com.life.services.admin.AppUserService;
import com.life.services.admin.AppUserTokenService;
import com.life.services.user.AppUserProfileService;
import com.life.supports.PasswordEncoder;
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
    public AppUser loadAppUserByAppUsername(String appUsername) {
        return this.appUserRepository.loadAppUserByAppUsername(appUsername);
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

        this.appEmailService.sendConfirmRegistrationEmail(appUser);
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
        this.appEmailService.sendConfirmRegistrationEmail(appUserToken.getAppUser());
    }

}