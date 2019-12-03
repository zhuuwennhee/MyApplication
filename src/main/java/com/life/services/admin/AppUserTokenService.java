package com.life.services.admin;

import com.life.entities.admin.AppUser;
import com.life.entities.admin.AppUserToken;

public interface AppUserTokenService {

    AppUserToken createConfirmationToken(AppUser appUser);

    AppUserToken remakeConfirmationToken(AppUser appUser);

}