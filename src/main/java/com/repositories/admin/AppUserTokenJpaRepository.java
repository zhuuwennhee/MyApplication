package com.repositories.admin;

import com.entities.admin.AppAction;
import com.entities.admin.AppUser;
import com.entities.admin.AppUserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserTokenJpaRepository extends JpaRepository<AppUserToken, Long> {

    AppUserToken findAppUserTokenByAppUserEqualsAndAppActionEquals(AppUser appUser, AppAction appAction);

    AppUserToken findAppUserTokenByAppUserTokenValueEquals(String appUserTokenValue);

    Long deleteAppUserTokenByAppUserEqualsAndAppActionEquals(AppUser appUser, AppAction appAction);

}