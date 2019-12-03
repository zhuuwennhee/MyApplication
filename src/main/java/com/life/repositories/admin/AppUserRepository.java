package com.life.repositories.admin;

import com.life.entities.admin.AppUser;

public interface AppUserRepository {

    AppUser loadAppUserByAppUsername(String appUsername);

    String signInAccount(AppUser appUser);

    boolean appUsernameExists(String appUsername);

    AppUser createAppUser(AppUser appUser);

}