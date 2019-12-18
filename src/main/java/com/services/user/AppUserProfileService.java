package com.services.user;

import com.entities.user.AppUserProfile;

public interface AppUserProfileService {

    AppUserProfile createAppUserProfile(AppUserProfile appUserProfile);

    AppUserProfile updateAppUserProfile(AppUserProfile appUserProfile);

    boolean emailAddressUsed(String emailAddress);

    String findEmailAddressByAppUsername(String appUsername);

}