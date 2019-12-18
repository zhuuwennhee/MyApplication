package com.repositories.user;

import com.entities.user.AppUserProfile;

public interface AppUserProfileRepository {

    AppUserProfile createAppUserProfile(AppUserProfile appUserProfile);

    AppUserProfile updateAppUserProfile(AppUserProfile appUserProfile);

    boolean emailAddressUsed(String emailAddress);

    String findEmailAddressByAppUsername(String appUsername);

}