package com.life.repositories.admin.impl;

import com.life.entities.admin.AppAction;
import com.life.entities.admin.AppUser;
import com.life.entities.admin.AppUserToken;
import com.life.repositories.admin.AppUserJpaRepository;
import com.life.repositories.admin.AppUserTokenJpaRepository;
import com.life.repositories.admin.AppUserTokenRepository;
import com.life.utils.DateTimeUtils;
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
     *  REMAKE CONFIRMATION TOKEN
     *  UPDATE NEW CONFIRMATION TOKEN
     *  UPDATE NEW GENERATED DATE
     *  UPDATE NEW EXPIRATION DATE
     */
    @Transactional
    @Override
    public AppUserToken remakeAppUserToken(AppUser appUser, AppAction appAction) {
        appUser = this.appUserJpaRepository.findAppUserByAppUsernameEquals(appUser.getAppUsername());
        AppUserToken appUserToken = new AppUserToken();
        appUserToken.setAppUserTokenValue(UUID.randomUUID().toString());
        appUserToken.setGeneratedDate(new Date());
        appUserToken.setExpirationDate(DateTimeUtils.plusDays(appUserToken.getGeneratedDate(), 7));
        return this.appUserTokenJpaRepository.saveAndFlush(appUserToken);
    }

}