package com.services.admin;

import com.entities.admin.AppUser;
import com.entities.admin.AppUserToken;

public interface AppEmailService {

    void sendConfirmRegistrationEmail(AppUser appUser, AppUserToken appUserToken);

    void sendEmailUnlockAppUser(AppUser appUser, AppUserToken appUserToken);

    void sendEmailForgotPassword(AppUser appUser, String password);

}