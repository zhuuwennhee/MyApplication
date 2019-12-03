package com.life.services.user;

import com.life.entities.user.AppUserProfile;

public interface AppUserProfileService {

    AppUserProfile createAppUserProfile(AppUserProfile appUserProfile);

    boolean emailAddressUsed(String emailAddress);

    String findEmailAddressByAppUsername(String appUsername);

    String confirmRegistration(String appUserTokenValue);

}