package com.repositories.user.impl;

import com.entities.user.AppUserProfile;
import com.repositories.user.AppUserProfileJpaRepository;
import com.repositories.user.AppUserProfileRepository;
import com.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository
public class AppUserProfileRepositoryImpl implements AppUserProfileRepository {

    @Autowired
    private AppUserProfileJpaRepository appUserProfileJpaRepository;

    /**
     *  CREATE APP USER PROFILE
     */
    @Transactional
    @Override
    public AppUserProfile createAppUserProfile(AppUserProfile appUserProfile) {
        appUserProfile.setFirstName(StringUtils.formatNoun(appUserProfile.getFirstName()));
        appUserProfile.setLastName(StringUtils.formatNoun(appUserProfile.getLastName()));
        return this.appUserProfileJpaRepository.saveAndFlush(appUserProfile);
    }

    /**
     *  UPDATE APP USER PROFILE
     */
    @Transactional
    @Override
    public AppUserProfile updateAppUserProfile(AppUserProfile appUserProfile) {
        appUserProfile.setFirstName(StringUtils.formatNoun(appUserProfile.getFirstName()));
        appUserProfile.setLastName(StringUtils.formatNoun(appUserProfile.getLastName()));
        appUserProfile.setLastUpdatedDate(new Date());
        return this.appUserProfileJpaRepository.saveAndFlush(appUserProfile);
    }

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
//        AppUser appUser = this.appUserJpaRepository.findAppUserByAppUsernameEquals(appUsername);
//        return appUser != null? appUser.getAppUserProfile().getEmailAddress() : null;
        return this.appUserProfileJpaRepository.findEmailAddressByAppUser_AppUsernameEquals(appUsername);
    }

}