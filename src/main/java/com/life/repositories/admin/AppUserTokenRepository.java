package com.life.repositories.admin;

import com.life.entities.admin.AppAction;
import com.life.entities.admin.AppUser;
import com.life.entities.admin.AppUserToken;

public interface AppUserTokenRepository {

    AppUserToken findAppUserTokenByAppUserTokenValue(String appUserTokenValue);

    AppUserToken createAppUserToken(AppUser appUser, AppAction appAction);

    AppUserToken remakeAppUserToken(AppUser appUser, AppAction appAction);

}