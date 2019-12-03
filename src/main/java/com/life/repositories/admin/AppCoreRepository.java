package com.life.repositories.admin;

import com.life.entities.admin.AppInfo;
import com.life.entities.admin.AppMenu;

import java.util.List;

public interface AppCoreRepository {

    AppInfo loadAppInfo();

    List<AppMenu> loadAppMenuByAppRoleName(String appRoleName);

}