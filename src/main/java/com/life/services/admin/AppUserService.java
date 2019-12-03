package com.life.services.admin;

import com.life.entities.admin.AppUser;

public interface AppUserService {

    AppUser loadAppUserByAppUsername(String appUsername);

    String signInAccount(AppUser appUser);

    boolean appUsernameExists(String appUsername);

    void createAppUser(AppUser appUser);

    void resendConfirmationToken(AppUser appUser);

}