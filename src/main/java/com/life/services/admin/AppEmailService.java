package com.life.services.admin;

import com.life.entities.admin.AppUser;

public interface AppEmailService {

    void sendConfirmRegistrationEmail(AppUser appUser);

}