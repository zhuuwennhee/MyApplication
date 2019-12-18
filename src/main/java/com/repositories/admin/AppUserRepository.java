package com.repositories.admin;

import com.entities.admin.AppUser;

public interface AppUserRepository {

    AppUser findAppUserByAppUsername(String appUsername);

    String signInAccount(AppUser appUser);

    boolean appUsernameExists(String appUsername);

    AppUser createAppUser(AppUser appUser);

    AppUser updateAppUser(AppUser appUser);

    String confirmRegistration(String appUserTokenValue);

    String unlockAppUser(String appUserTokenValue);

    void changePassword(AppUser appUser, String password);

}