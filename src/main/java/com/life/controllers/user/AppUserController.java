package com.life.controllers.user;

import com.life.services.user.AppUserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Controller
public class AppUserController {

    @Autowired
    private AppUserProfileService appUserProfileService;

    /**
     *  CONFIRM REGISTRATION ACCOUNT ACTION
     */
    @GetMapping(value = {"/ConfirmRegistration/{appUserTokenValue}"})
    public String confirmRegistrationAction(@PathVariable(name = "appUserTokenValue") @NotNull @NotEmpty String appUserTokenValue) {
        return "redirect:/confirm-registration.html?status=" + this.appUserProfileService.confirmRegistration(appUserTokenValue);
    }

    /**
     *  VISIT CONFIRM REGISTRATION
     *  SHOW MESSAGE SUCCESS OR ERROR ACTION CONFIRM
     */
    @GetMapping(value = {"/confirm-registration.html"})
    public String visitConfirmRegistration() {
        return "/user/confirm-registration";
    }

}