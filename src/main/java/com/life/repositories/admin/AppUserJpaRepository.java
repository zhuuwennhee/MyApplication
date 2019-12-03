package com.life.repositories.admin;

import com.life.entities.admin.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserJpaRepository extends JpaRepository<AppUser, Long> {

    AppUser findAppUserByAppUsernameEquals(String appUsername);

}