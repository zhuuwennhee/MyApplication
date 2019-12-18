package com.supports;

import com.consts.AppAttribute;
import com.consts.AppRole;
import com.entities.admin.AppInfo;
import com.entities.admin.AppMenu;
import com.entities.admin.AppUser;
import com.services.admin.AppCoreService;
import com.services.admin.AppUserRoleService;
import com.services.admin.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Service
public class WebSupportService {

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private AppCoreService appCoreService;

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private AppUserRoleService appUserRoleService;

    /**
     *  PROCESS POST VISIT PUBLIC PAGE
     *  LOAD APP INFO
     *  CHECK IF USER SIGN-INNED -> LOAD USER INFO
     *  LOAD ALL MENU BY ROLE
     */
    public void postVisitPublicPage(Principal principal) {
        /** LOGIN HAS PRINCIPAL */
        User user = principal != null?(User) ((Authentication) principal).getPrincipal() : null;
        /** LOAD APP INFO CONFIG */
        AppInfo appInfo = this.appCoreService.loadAppInfo();
        this.httpSession.setAttribute(AppAttribute.APP_INFO, appInfo);
        String appRoleName = AppRole.ROLE_DEFAULT;
        if(user != null) {
            appRoleName = this.appUserRoleService.getAppRoleNameListByAppUsername(user.getUsername()).stream().findFirst().orElse(AppRole.ROLE_DEFAULT);
            AppUser appUser = this.appUserService.findAppUserByAppUsername(user.getUsername());
            this.httpSession.setAttribute(AppAttribute.SIGN_INNED_USER, appUser);
        }
        /** LOAD ALL MENU FOR  ROLE */
        List<AppMenu> appMenuList = this.appCoreService.loadAppMenuByAppRoleName(appRoleName);
        this.httpSession.setAttribute(AppAttribute.APP_MENU_LIST, appMenuList);
    }

}