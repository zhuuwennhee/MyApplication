package com.life.repositories.admin;

import com.life.entities.admin.AppUserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserTokenJpaRepository extends JpaRepository<AppUserToken, Long> {

    AppUserToken findAppUserTokenByAppUserTokenValueEquals(String appUserTokenValue);

}