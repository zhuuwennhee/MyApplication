package com.services.admin.impl;

import com.entities.admin.AppInfo;
import com.entities.admin.AppMenu;
import com.repositories.admin.AppCoreRepository;
import com.services.admin.AppCoreService;
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