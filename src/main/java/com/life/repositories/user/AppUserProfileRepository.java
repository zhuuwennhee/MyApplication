package com.life.repositories.user;

public interface AppUserProfileRepository {

    boolean emailAddressUsed(String emailAddress);

    String findEmailAddressByAppUsername(String appUsername);

    String confirmRegistration(String appUserTokenValue);

}