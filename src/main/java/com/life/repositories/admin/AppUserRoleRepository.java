package com.life.repositories.admin;

import com.life.entities.admin.AppUser;
import com.life.entities.admin.AppUserRole;

import java.util.List;
import java.util.Set;

public interface AppUserRoleRepository {

    List<String> getAppRoleNameListByAppUserId(Long appUserId);

    List<String> getAppRoleNameListByAppUsername(String appUsername);

    void createAppUserRoleList(Set<AppUserRole> appUserRoleSet, AppUser appUser);

}