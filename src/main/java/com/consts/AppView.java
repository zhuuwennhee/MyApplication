package com.consts;

public interface AppView {

    /**
     *  PUBLIC PAGE VIEW NAME
     */
    String INDEX = "/app/index";
    String LOGIN = "/app/login";

    /**
     *  USER PAGE VIEW NAME
     */
    String CONFIRM_REGISTRATION = "/user/confirm-registration";
    String UNLOCK_APP_USER = "/user/unlock-app-user";

    /**
     *  ERROR PAGE VIEW NAME
     */
    String ERROR_403 = "/error/403";
    String ERROR_404 = "/error/404";
    String ERROR_500 = "/error/500";

}