package com.services.admin;

import com.entities.admin.AppInfo;
import com.entities.admin.AppMenu;

import java.util.List;

public interface AppCoreService {

    AppInfo loadAppInfo();

    List<AppMenu> loadAppMenuByAppRoleName(String appRoleName);

}