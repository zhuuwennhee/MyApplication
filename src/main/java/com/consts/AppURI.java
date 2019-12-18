package com.consts;

public interface AppURI {

    /**
     *  PUBLIC PAGE
     */
    String ROOT = "/";
    String INDEX = "/app/index.html";
    String LOGIN = "/app/login.html";

    /**
     *  SIGN-INNED ALLOW
     */
    String CONFIRM_REGISTRATION = "/user/confirm-registration.html";
    String UNLOCK_APP_USER = "/user/unlock-app-user.html";
    String PROFILE = "/user/profile.html";

    /**
     *  ADMINISTRATOR PAGE
     */
    String USER_MANAGER = "/administrator/user-manager.html";
    String ADMINISTRATOR = "/administrator/administrator.html";
    String APP_INFO = "/administrator/app-info.html";

    /**
     *  ERROR PAGE
     */
    String ERROR_403 = "/error/403.html";
    String ERROR_404 = "/error/404.html";
    String ERROR_500 = "/error/500.html";

}