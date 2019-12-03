package com.life.repositories.user.impl;

import com.life.consts.ResultStatus;
import com.life.entities.admin.AppUser;
import com.life.entities.admin.AppUserToken;
import com.life.repositories.admin.AppUserJpaRepository;
import com.life.repositories.admin.AppUserTokenRepository;
import com.life.repositories.user.AppUserProfileJpaRepository;
import com.life.repositories.user.AppUserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository
public class AppUserProfileRepositoryImpl implements AppUserProfileRepository {

    @Autowired
    private AppUserJpaRepository appUserJpaRepository;

    @Autowired
    private AppUserProfileJpaRepository appUserProfileJpaRepository;

    @Autowired
    private AppUserTokenRepository appUserTokenRepository;

    /**
     *  CHECK EMAIL ADDRESS USED
     *  USED -> RETURN TRUE ELSE RETURN FALSE
     */
    @Transactional
    @Override
    public boolean emailAddressUsed(String emailAddress) { ;
        return this.appUserProfileJpaRepository.findAppUserProfileByEmailAddressEquals(emailAddress) != null;
    }

    /**
     *  FIND EMAIL ADDRESS BY USERNAME
     */
    @Transactional
    @Override
    public String findEmailAddressByAppUsername(String appUsername) {
        AppUser appUser = this.appUserJpaRepository.findAppUserByAppUsernameEquals(appUsername);
        return appUser != null? appUser.getAppUserProfile().getEmailAddress() : null;
    }

    /**
     *  CONFIRM REGISTRATION ACCOUNT
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

}