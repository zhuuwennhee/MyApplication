package com.life.services.admin.impl;

import com.life.entities.admin.AppInfo;
import com.life.entities.admin.AppMenu;
import com.life.repositories.admin.AppCoreRepository;
import com.life.services.admin.AppCoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AppCoreServiceImpl implements AppCoreService {

    @Autowired
    private AppCoreRepository appCoreRepository;

    @Transactional
    @Override
    public AppInfo loadAppInfo() {
        return this.appCoreRepository.loadAppInfo();
    }

    @Transactional
    @Override
    public List<AppMenu> loadAppMenuByAppRoleName(String appRoleName) {
        return this.appCoreRepository.loadAppMenuByAppRoleName(appRoleName);
    }

}