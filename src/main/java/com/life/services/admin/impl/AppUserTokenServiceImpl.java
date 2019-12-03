package com.life.services.admin.impl;

import com.life.entities.admin.AppAction;
import com.life.entities.admin.AppUser;
import com.life.entities.admin.AppUserToken;
import com.life.repositories.admin.AppUserTokenRepository;
import com.life.services.admin.AppUserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AppUserTokenServiceImpl implements AppUserTokenService {

    @Autowired
    private AppUserTokenRepository appUserTokenRepository;

    /**
     *  CREATE CONFIRMATION TOKEN
     */
    @Transactional
    @Override
    public AppUserToken createConfirmationToken(AppUser appUser) {
        AppAction appAction = new AppAction();
        return this.appUserTokenRepository.createAppUserToken(appUser, appAction);
    }

    /**
     *  REMAKE CONFIRMATION TOKEN
     */
    @Transactional
    @Override
    public AppUserToken remakeConfirmationToken(AppUser appUser) {
        AppAction appAction = new AppAction();
        return this.appUserTokenRepository.remakeAppUserToken(appUser, appAction);
    }

}