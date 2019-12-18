package com.repositories.admin;

import com.entities.admin.AppInfo;
import com.entities.admin.AppMenu;

import java.util.List;

public interface AppCoreRepository {

    AppInfo loadAppInfo();

    List<AppMenu> loadAppMenuByAppRoleName(String appRoleName);

}