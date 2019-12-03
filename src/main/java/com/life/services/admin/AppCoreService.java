package com.life.services.admin;

import com.life.entities.admin.AppInfo;
import com.life.entities.admin.AppMenu;

import java.util.List;

public interface AppCoreService {

    AppInfo loadAppInfo();

    List<AppMenu> loadAppMenuByAppRoleName(String appRoleName);

}