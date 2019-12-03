package com.life.repositories.user;

import com.life.entities.user.AppUserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserProfileJpaRepository extends JpaRepository<AppUserProfile, Long> {

    AppUserProfile findAppUserProfileByEmailAddressEquals(String emailAddress);

}