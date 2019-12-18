package com.repositories.user;

import com.entities.user.AppUserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserProfileJpaRepository extends JpaRepository<AppUserProfile, Long> {

    AppUserProfile findAppUserProfileByEmailAddressEquals(String emailAddress);

    String findEmailAddressByAppUser_AppUsernameEquals(String appUsername);

}