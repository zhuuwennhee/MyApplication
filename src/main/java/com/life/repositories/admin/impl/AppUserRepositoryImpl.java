package com.life.repositories.admin.impl;

import com.life.consts.ResultStatus;
import com.life.entities.admin.AppUser;
import com.life.repositories.admin.AppUserJpaRepository;
import com.life.repositories.admin.AppUserRepository;
import com.life.supports.PasswordEncoder;
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

    /**
     *  FIND USER BY USER_NAME
     */
    @Transactional
    @Override
    public AppUser loadAppUserByAppUsername(String appUsername) {
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

}