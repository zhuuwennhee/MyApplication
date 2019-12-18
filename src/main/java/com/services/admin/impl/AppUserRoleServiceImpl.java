package com.services.admin.impl;

import com.entities.admin.AppUser;
import com.entities.admin.AppUserRole;
import com.repositories.admin.AppUserRoleRepository;
import com.services.admin.AppUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class AppUserRoleServiceImpl implements AppUserRoleService {

    @Autowired
    private AppUserRoleRepository appUserRoleRepository;

    /**
     *  LOAD ROLE NAME FOR USER BY USER ID
     */
    @Transactional
    @Override
    public List<String> getAppRoleNameListByAppUserId(Long appUserId) {
        return this.appUserRoleRepository.getAppRoleNameListByAppUserId(appUserId);
    }

    /**
     *  LOAD ROLE NAME FOR USER BY USER NAME
     */
    @Transactional
    @Override
    public List<String> getAppRoleNameListByAppUsername(String appUsername) {
        return this.appUserRoleRepository.getAppRoleNameListByAppUsername(appUsername);
    }

    /**
     *  INSERT LIST APP ROLE OF APP USER
     */
    @Transactional
    @Override
    public void createAppUserRoleList(Set<AppUserRole> appUserRoleSet, AppUser appUser) {
        this.appUserRoleRepository.createAppUserRoleList(appUserRoleSet, appUser);
    }

}