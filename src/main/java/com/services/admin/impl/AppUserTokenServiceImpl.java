package com.services.admin.impl;

import com.entities.admin.AppAction;
import com.entities.admin.AppUser;
import com.entities.admin.AppUserToken;
import com.repositories.admin.AppActionJpaRepository;
import com.repositories.admin.AppUserTokenRepository;
import com.services.admin.AppUserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AppUserTokenServiceImpl implements AppUserTokenService {

    @Autowired
    private AppUserTokenRepository appUserTokenRepository;

    @Autowired
    private AppActionJpaRepository appActionJpaRepository;

    /**
     *  CREATE CONFIRMATION TOKEN
     */
    @Transactional
    @Override
    public AppUserToken createConfirmationToken(AppUser appUser) {
        AppAction appAction = this.appActionJpaRepository.findAppActionByAppActionNameEquals(com.consts.AppAction.CONFIRM_REGISTRATION);
        return this.appUserTokenRepository.createAppUserToken(appUser, appAction);
    }

    /**
     *  REMAKE CONFIRMATION TOKEN
     */
    @Transactional
    @Override
    public AppUserToken remakeConfirmationToken(AppUser appUser) {
        return this.appUserTokenRepository.remakeAppUserToken(appUser, new AppAction(com.consts.AppAction.CONFIRM_REGISTRATION));
    }

    /**
     *  CREATE UNLOCK APP USER TOKEN
     */
    @Transactional
    @Override
    public AppUserToken createUnlockAppUserToken(AppUser appUser) {
        AppAction appAction = this.appActionJpaRepository.findAppActionByAppActionNameEquals(com.consts.AppAction.UNLOCK_APP_USER);
        this.appUserTokenRepository.deleteAppUserTokenByAppUserEqualsAndAppActionEquals(appUser, appAction);
        return this.appUserTokenRepository.createAppUserToken(appUser, appAction);
    }

}