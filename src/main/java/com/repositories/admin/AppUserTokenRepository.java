package com.repositories.admin;

import com.entities.admin.AppAction;
import com.entities.admin.AppUser;
import com.entities.admin.AppUserToken;

public interface AppUserTokenRepository {

    AppUserToken findAppUserTokenByAppUserTokenValue(String appUserTokenValue);

    AppUserToken createAppUserToken(AppUser appUser, AppAction appAction);

    AppUserToken remakeAppUserToken(AppUser appUser, AppAction appAction);

    void deleteAppUserTokenByAppUserEqualsAndAppActionEquals(AppUser appUser, AppAction appAction);

}