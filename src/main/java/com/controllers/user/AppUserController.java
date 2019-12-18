package com.controllers.user;

import com.consts.*;
import com.services.admin.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Controller
public class AppUserController {

    @Autowired
    private AppUserService appUserService;

    private String confirmRegistrationStatus;

    private String unlockAppUserStatus;

    /**
     *  CONFIRM REGISTRATION ACCOUNT ACTION
     */
    @GetMapping(value = {AppActionController.CONFIRM_REGISTRATION})
    public String confirmRegistrationAction(@PathVariable(name = PathVariableName.APP_USER_TOKEN_VALUE) @NotNull @NotEmpty String appUserTokenValue) {
        this.confirmRegistrationStatus = this.appUserService.confirmRegistration(appUserTokenValue);
        return "redirect:" + AppURI.CONFIRM_REGISTRATION;
    }

    /**
     *  VISIT CONFIRM REGISTRATION
     *  SHOW ACTION MESSAGE
     */
    @GetMapping(value = {AppURI.CONFIRM_REGISTRATION})
    public String visitConfirmRegistration(HttpServletRequest httpServletRequest) {
        httpServletRequest.setAttribute(AppAttribute.CONFIRM_REGISTRATION_STATUS, this.confirmRegistrationStatus);
        this.confirmRegistrationStatus = null;
        return AppView.CONFIRM_REGISTRATION;
    }

    /**
     *  UNLOCK APP USER ACTION
     */
    @GetMapping(value = {AppActionController.UNLOCK_APP_USER})
    public String unlockAppUserAction(@PathVariable(name = PathVariableName.APP_USER_TOKEN_VALUE) @NotNull @NotEmpty String appUserTokenValue) {
        this.unlockAppUserStatus = this.appUserService.unlockAppUser(appUserTokenValue);
        return "redirect:" + AppURI.UNLOCK_APP_USER;
    }

    /**
     *  VISIT UNLOCK APP USER
     *  SHOW ACTION MESSAGE
     */
    @GetMapping(value = {AppURI.UNLOCK_APP_USER})
    public String visitUnlockAppUser(HttpServletRequest httpServletRequest) {
        httpServletRequest.setAttribute(AppAttribute.UNLOCK_APP_USER_STATUS, this.unlockAppUserStatus);
        this.unlockAppUserStatus = null;
        return AppView.UNLOCK_APP_USER;
    }

}