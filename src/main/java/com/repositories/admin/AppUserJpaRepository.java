package com.repositories.admin;

import com.entities.admin.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserJpaRepository extends JpaRepository<AppUser, Long> {

    AppUser findAppUserByAppUsernameEquals(String appUsername);

}