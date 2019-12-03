package com.life.controllers.user;

import com.google.gson.Gson;
import com.life.entities.admin.AppUser;
import com.life.services.admin.AppUserService;
import com.life.services.user.AppUserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class AppUserRestController {

    private static final Logger LOGGER = Logger.getLogger("APP_USER_REST_CONTROLLER");

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private AppUserProfileService appUserProfileService;

    @PostMapping(value = {"/SignInAccount"})
    public ResponseEntity<String> signInAccount(@RequestBody @NotNull AppUser appUser) {
        return new ResponseEntity<>(new Gson().toJson(this.appUserService.signInAccount(appUser)), HttpStatus.OK);
    }

    /**
     *  CHECK USERNAME EXISTS
     *  IF EXISTS RETURN TRUE
     *  IF NOT EXISTS RETURN FALSE
     */
    @GetMapping(value = {"/AppUsernameExists"})
    public ResponseEntity<Boolean> usernameExists(@RequestParam(name = "appUsername") @NotNull @NotEmpty String appUsername) {
        return new ResponseEntity<>(this.appUserService.appUsernameExists(appUsername), HttpStatus.OK);
    }

    /**
     *  CHECK EMAIL ADDRESS USED
     *  IF USED RETURN TRUE
     *  ELSE RETURN FALSE
     */
    @GetMapping(value = {"/EmailAddressUsed"})
    public ResponseEntity<Boolean> emailAddressUsed(@RequestParam(name = "emailAddress") @NotNull @NotEmpty @Email String emailAddress) {
        return new ResponseEntity<>(this.appUserProfileService.emailAddressUsed(emailAddress), HttpStatus.OK);
    }

    /**
     *  CREATE APP USER
     */
    @PostMapping(value = {"/CreateAppUser"})
    public ResponseEntity<Boolean> createAppUser(@RequestBody @NotNull AppUser appUser) {
        boolean flag = false;
        try {
            this.appUserService.createAppUser(appUser);
            flag = true;
        }
        catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage());
        }
        return new ResponseEntity<>(flag, HttpStatus.OK);
    }

    /**
     *  FIND EMAIL ADDRESS BY USERNAME
     */
    @GetMapping(value = {"/FindEmailAddressByAppUsername"})
    public ResponseEntity<String> findEmailAddressByUsername(@RequestParam(name = "appUsername") @NotNull @NotEmpty String appUsername) {
        return new ResponseEntity<>(new Gson().toJson(this.appUserProfileService.findEmailAddressByAppUsername(appUsername)), HttpStatus.OK);
    }

    /**
     *  RESEND CONFIRMATION TOKEN REGISTRATION
     */
    @PutMapping(value = {"/ResendConfirmationToken"})
    public ResponseEntity<Boolean> resendConfirmationToken(@RequestBody @NotNull AppUser appUser) {
        boolean flag = false;
        try {
            this.appUserService.resendConfirmationToken(appUser);
            flag = true;
        }
        catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage());
        }
        return new ResponseEntity<>(flag, HttpStatus.OK);
    }

    /**
     *  SEND EMAIL UNLOCK ACCOUNT
     */
    @PostMapping(value = {"/SendEmailUnlockAppUser"})
    public ResponseEntity<Boolean> sendEmailUnlockAppUser(@RequestBody @NotNull AppUser appUser) {
        return new ResponseEntity<>(false, HttpStatus.OK);
    }

}