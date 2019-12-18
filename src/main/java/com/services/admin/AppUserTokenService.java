package com.services.admin;

import com.entities.admin.AppUser;
import com.entities.admin.AppUserToken;

public interface AppUserTokenService {

    AppUserToken createConfirmationToken(AppUser appUser);

    AppUserToken remakeConfirmationToken(AppUser appUser);

    AppUserToken createUnlockAppUserToken(AppUser appUser);

}