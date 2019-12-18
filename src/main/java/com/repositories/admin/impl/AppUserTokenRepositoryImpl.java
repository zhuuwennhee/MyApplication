package com.repositories.admin.impl;

import com.entities.admin.AppAction;
import com.entities.admin.AppUser;
import com.entities.admin.AppUserToken;
import com.repositories.admin.AppActionJpaRepository;
import com.repositories.admin.AppUserJpaRepository;
import com.repositories.admin.AppUserTokenJpaRepository;
import com.repositories.admin.AppUserTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Repository
public class AppUserTokenRepositoryImpl implements AppUserTokenRepository {

    @Autowired
    private AppUserTokenJpaRepository appUserTokenJpaRepository;

    @Autowired
    private AppUserJpaRepository appUserJpaRepository;

    @Autowired
    private AppActionJpaRepository appActionJpaRepository;

    /**
     *  FIND CONFIRMATION TOKEN BY CONFIRMATION TOKEN STRING
     */
    @Transactional
    @Override
    public AppUserToken findAppUserTokenByAppUserTokenValue(String appUserTokenValue) {
        return this.appUserTokenJpaRepository.findAppUserTokenByAppUserTokenValueEquals(appUserTokenValue);
    }

    /**
     *  CREATE CONFIRMATION TOKEN
     */
    @Override
    public AppUserToken createAppUserToken(AppUser appUser, AppAction appAction) {
        return this.appUserTokenJpaRepository.saveAndFlush(new AppUserToken(appUser, appAction));
    }

    /**
     *  DELETE APP USER TOKEN BY APP USER EQUALS AND APP ACTION EQUALS
     */
    @Transactional
    @Override
    public void deleteAppUserTokenByAppUserEqualsAndAppActionEquals(AppUser appUser, AppAction appAction) {
        this.appUserTokenJpaRepository.deleteAppUserTokenByAppUserEqualsAndAppActionEquals(appUser, appAction);
    }

    /**
     *  REMAKE CONFIRMATION TOKEN
     *  UPDATE NEW CONFIRMATION TOKEN
     *  UPDATE NEW GENERATED DATE
     *  UPDATE NEW EXPIRATION DATE
     */
    @Transactional
    @Override
    public AppUserToken remakeAppUserToken(AppUser appUser, AppAction appAction) {
        appUser = this.appUserJpaRepository.findAppUserByAppUsernameEquals(appUser.getAppUsername());
        appAction = this.appActionJpaRepository.findAppActionByAppActionNameEquals(appAction.getAppActionName());
        AppUserToken appUserToken = this.appUserTokenJpaRepository.findAppUserTokenByAppUserEqualsAndAppActionEquals(appUser, appAction);
        appUserToken.setAppUserTokenValue(UUID.randomUUID().toString());
        appUserToken.setGeneratedDate(new Date());
        return this.appUserTokenJpaRepository.saveAndFlush(appUserToken);
    }

}