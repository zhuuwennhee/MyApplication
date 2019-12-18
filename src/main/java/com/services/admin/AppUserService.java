package com.services.admin;

import com.entities.admin.AppUser;

public interface AppUserService {

    AppUser findAppUserByAppUsername(String appUsername);

    String signInAccount(AppUser appUser);

    boolean appUsernameExists(String appUsername);

    void createAppUser(AppUser appUser);

    void resendConfirmationToken(AppUser appUser);

    void sendEmailUnlockAppUser(AppUser appUser);

    String confirmRegistration(String appUserTokenValue);

    void sendEmailForgotPassword(AppUser appUser);

    String unlockAppUser(String appUserTokenValue);

    boolean passwordUsing(AppUser appUser, String password);

    void changePassword(AppUser appUser, String password);

}