package com.services.admin;

import com.entities.admin.AppUser;
import com.entities.admin.AppUserRole;

import java.util.List;
import java.util.Set;

public interface AppUserRoleService {

    List<String> getAppRoleNameListByAppUserId(Long appUserId);

    List<String> getAppRoleNameListByAppUsername(String appUsername);

    void createAppUserRoleList(Set<AppUserRole> appUserRoleSet, AppUser appUser);

}