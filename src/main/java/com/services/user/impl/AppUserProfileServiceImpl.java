package com.services.user.impl;

import com.entities.user.AppUserProfile;
import com.repositories.user.AppUserProfileRepository;
import com.services.user.AppUserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AppUserProfileServiceImpl implements AppUserProfileService {

    @Autowired
    private AppUserProfileRepository appUserProfileRepository;

    /**
     *  INSERT APP USER PROFILE
     */
    @Transactional
    @Override
    public AppUserProfile createAppUserProfile(AppUserProfile appUserProfile) {
        return this.appUserProfileRepository.createAppUserProfile(appUserProfile);
    }

    /**
     *  UPDATE APP USER PROFILE
     */
    @Override
    public AppUserProfile updateAppUserProfile(AppUserProfile appUserProfile) {
        try {
            appUserProfile = this.appUserProfileRepository.updateAppUserProfile(appUserProfile);
        }
        catch (Exception ex) {
            appUserProfile = null;
        }
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

}