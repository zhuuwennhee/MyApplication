package com.life.services.user.impl;

import com.life.entities.user.AppUserProfile;
import com.life.repositories.user.AppUserProfileJpaRepository;
import com.life.repositories.user.AppUserProfileRepository;
import com.life.services.user.AppUserProfileService;
import com.life.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AppUserProfileServiceImpl implements AppUserProfileService {

    @Autowired
    private AppUserProfileRepository appUserProfileRepository;

    @Autowired
    private AppUserProfileJpaRepository appUserProfileJpaRepository;

    /**
     *  INSERT APP USER PROFILE
     */
    @Override
    public AppUserProfile createAppUserProfile(AppUserProfile appUserProfile) {
        appUserProfile.setFirstName(StringUtils.formatNoun(appUserProfile.getFirstName()));
        appUserProfile.setLastName(StringUtils.formatNoun(appUserProfile.getLastName()));
        appUserProfile = this.appUserProfileJpaRepository.saveAndFlush(appUserProfile);
        return appUserProfile;
    }

    /**
     *  CHECK EMAIL ADDRESS USED
     *  IF USED RETURN TRUE, ELSE RETURN FALSE
     */
    @Transactional
    @Override
    public boolean emailAddressUsed(String emailAddress) {
        return this.appUserProfileRepository.emailAddressUsed(emailAddress);
    }

    /**
     *  FIND EMAIL ADDRESS BY APP USERNAME
     */
    @Transactional
    @Override
    public String findEmailAddressByAppUsername(String appUsername) {
        return this.appUserProfileRepository.findEmailAddressByAppUsername(appUsername);
    }

    /**
     *  CONFIRM REGISTRATION ACCOUNT
     */
    @Transactional
    @Override
    public String confirmRegistration(String appUserTokenValue) {
        return this.appUserProfileRepository.confirmRegistration(appUserTokenValue);
    }

}