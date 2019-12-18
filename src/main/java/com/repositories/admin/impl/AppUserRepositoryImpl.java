package com.repositories.admin.impl;

import com.consts.ResultStatus;
import com.entities.admin.AppUser;
import com.entities.admin.AppUserToken;
import com.repositories.admin.AppUserJpaRepository;
import com.repositories.admin.AppUserRepository;
import com.repositories.admin.AppUserTokenRepository;
import com.supports.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository
public class AppUserRepositoryImpl implements AppUserRepository {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AppUserJpaRepository appUserJpaRepository;

    @Autowired
    private AppUserTokenRepository appUserTokenRepository;

    /**
     *  FIND USER BY USER_NAME
     */
    @Transactional
    @Override
    public AppUser findAppUserByAppUsername(String appUsername) {
        return this.appUserJpaRepository.findAppUserByAppUsernameEquals(appUsername);
    }

    /**
     *  SIGN-IN ACCOUNT
     */
    @Transactional
    @Override
    public String signInAccount(AppUser appUser) {
        String encryptedPassword = appUser.getEncryptedPassword();
        appUser = this.appUserJpaRepository.findAppUserByAppUsernameEquals(appUser.getAppUsername());
        String resultStatus = ResultStatus.SUCCESS;
        if(appUser != null && this.passwordEncoder.matches(encryptedPassword, appUser.getEncryptedPassword())) {
            if(!appUser.isEnabled()) {
                resultStatus = ResultStatus.DISABLED;
            }
            if(appUser.isLocked()) {
                resultStatus = ResultStatus.LOCKED;
            }
        }
        else {
            resultStatus = ResultStatus.INCORRECT;
        }
        return resultStatus;
    }

    /**
     *  CHECK USERNAME EXISTS
     *  EXISTS RETURN TRUE ELSE RETURN FALSE
     */
    @Transactional
    @Override
    public boolean appUsernameExists(String appUsername) {
        return this.appUserJpaRepository.findAppUserByAppUsernameEquals(appUsername) != null;
    }

    /**
     *  INSERT APP USER
     */
    @Transactional
    @Override
    public AppUser createAppUser(AppUser appUser) {
        appUser.setEncryptedPassword(this.passwordEncoder.encryptPassword(appUser.getEncryptedPassword()));
        appUser.setAppUserProfile(null);
        appUser.setAppUserRoleSet(null);
        appUser.setEnabled(false);
        appUser.setCreatedDate(new Date());
        return this.appUserJpaRepository.saveAndFlush(appUser);
    }

    /**
     *  UPDATE APP USER
     */
    @Transactional
    @Override
    public AppUser updateAppUser(AppUser appUser) {
        return this.appUserJpaRepository.saveAndFlush(appUser);
    }

    /**
     *  CONFIRM REGISTRATION APP USER
     */
    @Transactional
    @Override
    public String confirmRegistration(String appUserTokenValue) {
        String result = ResultStatus.ERROR;
        /** FIND APP USER BY CONFIRMATION TOKEN **/
        AppUserToken appUserToken = this.appUserTokenRepository.findAppUserTokenByAppUserTokenValue(appUserTokenValue);
        if(appUserToken != null) {
            AppUser appUser = appUserToken.getAppUser();
            /** IF APP USER ENABLED -> OUT **/
            if(appUser.isEnabled()) {
                result = ResultStatus.ENABLED;
            }
            else {
                /** IF THE TOKEN HAS NOT EXPIRED **/
                if (appUserToken.getExpirationDate().after(new Date())) {
                    /** UPDATE ENABLE APP USER **/
                    appUser.setEnabled(true);
                    appUser.setEnabledDate(new Date());
                    this.appUserJpaRepository.saveAndFlush(appUser);
                    result = ResultStatus.SUCCESS;
                }
            }
        }
        return result;
    }

    /**
     *  UNLOCK APP USER
     */
    @Transactional
    @Override
    public String unlockAppUser(String appUserTokenValue) {
        String result = ResultStatus.ERROR;
        /** FIND APP USER BY CONFIRMATION TOKEN **/
        AppUserToken appUserToken = this.appUserTokenRepository.findAppUserTokenByAppUserTokenValue(appUserTokenValue);
        if(appUserToken != null) {
            AppUser appUser = appUserToken.getAppUser();
            /** IF APP USER LOCKED -> OUT **/
            if(appUser.isLocked()) {
                /** IF THE TOKEN HAS NOT EXPIRED **/
                if (appUserToken.getExpirationDate().after(new Date())) {
                    /** UPDATE UNLOCKED APP USER **/
                    appUser.setLocked(false);
                    appUser.setUnlockedDate(new Date());
                    this.appUserJpaRepository.saveAndFlush(appUser);
                    result = ResultStatus.SUCCESS;
                }
            }
            else {
                result = ResultStatus.UNLOCKED;
            }
        }
        return result;
    }

    /**
     *  CHANGE PASSWORD
     *  FIND APP USER
     *  UPDATE NEW PASSWORD
     */
    @Transactional
    @Override
    public void changePassword(AppUser appUser, String password) {
        appUser = this.appUserJpaRepository.findById(appUser.getAppUserId()).get();
        appUser.setEncryptedPassword(this.passwordEncoder.encryptPassword(password));
        this.appUserJpaRepository.saveAndFlush(appUser);
    }

}