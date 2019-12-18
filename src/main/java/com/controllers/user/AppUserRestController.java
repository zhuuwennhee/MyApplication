package com.controllers.user;

import com.consts.AppActionController;
import com.consts.AppAttribute;
import com.consts.AppRequestParamName;
import com.entities.admin.AppUser;
import com.entities.user.AppUserProfile;
import com.google.gson.Gson;
import com.services.admin.AppUserService;
import com.services.user.AppUserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class AppUserRestController {

    private static final Logger LOGGER = Logger.getLogger(AppUserRestController.class.getName());

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private AppUserProfileService appUserProfileService;

    @Autowired
    private HttpSession httpSession;

    /**
     *  GET SIGN INNED APP USER IN SESSION
     */
    @GetMapping(value = {AppActionController.GET_SIGN_INNED_APP_USER})
    public ResponseEntity<AppUser> getSignInnedAppUser() {
        return new ResponseEntity<>((AppUser)this.httpSession.getAttribute(AppAttribute.SIGN_INNED_USER), HttpStatus.OK);
    }

    @PostMapping(value = {AppActionController.SIGN_IN_APP_USER})
    public ResponseEntity<String> signInAppUser(@RequestBody @NotNull AppUser appUser) {
        return new ResponseEntity<>(new Gson().toJson(this.appUserService.signInAccount(appUser)), HttpStatus.OK);
    }

    /**
     *  CHECK USERNAME EXISTS
     *  IF EXISTS RETURN TRUE
     *  IF NOT EXISTS RETURN FALSE
     */
    @GetMapping(value = {AppActionController.APP_USER_NAME_EXISTS})
    public ResponseEntity<Boolean> usernameExists(@RequestParam(name = AppRequestParamName.APP_USERNAME) @NotNull @NotEmpty String appUsername) {
        return new ResponseEntity<>(this.appUserService.appUsernameExists(appUsername), HttpStatus.OK);
    }

    /**
     *  CHECK EMAIL ADDRESS USED
     *  IF USED RETURN TRUE
     *  ELSE RETURN FALSE
     */
    @GetMapping(value = {AppActionController.EMAIL_ADDRESS_USED})
    public ResponseEntity<Boolean> emailAddressUsed(@RequestParam(name = AppRequestParamName.EMAIL_ADDRESS) @NotNull @NotEmpty @Email String emailAddress) {
        return new ResponseEntity<>(this.appUserProfileService.emailAddressUsed(emailAddress), HttpStatus.OK);
    }

    /**
     *  CREATE APP USER
     */
    @PostMapping(value = {AppActionController.CREATE_APP_USER})
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
    @GetMapping(value = {AppActionController.FIND_EMAIL_ADDRESS_BY_APP_USERNAME})
    public ResponseEntity<String> findEmailAddressByUsername(@RequestParam(name = AppRequestParamName.APP_USERNAME) @NotNull @NotEmpty String appUsername) {
        return new ResponseEntity<>(new Gson().toJson(this.appUserProfileService.findEmailAddressByAppUsername(appUsername)), HttpStatus.OK);
    }

    /**
     *  RESEND CONFIRMATION TOKEN REGISTRATION
     */
    @PutMapping(value = {AppActionController.RESEND_CONFIRMATION_TOKEN})
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
     *  SEND EMAIL UNLOCK APP USER
     */
    @PutMapping(value = {AppActionController.SEND_EMAIL_UNLOCK_APP_USER})
    public ResponseEntity<Boolean> sendEmailUnlockAppUser(@RequestBody @NotNull AppUser appUser) {
        boolean flag = false;
        try {
            this.appUserService.sendEmailUnlockAppUser(appUser);
            flag = true;
        }
        catch (Exception ex) {
            LOGGER.log(Level.WARNING, ex.getMessage());
        }
        return new ResponseEntity<>(flag, HttpStatus.OK);
    }

    /**
     *  SEND EMAIL FORGOT PASSWORD
     */
    @PutMapping(value = {AppActionController.SEND_EMAIL_FORGOT_PASSWORD})
    public ResponseEntity<Boolean> sendEmailForgotPassword(@RequestBody @NotNull @NotEmpty AppUser appUser) {
        boolean flag = false;
        try {
            this.appUserService.sendEmailForgotPassword(appUser);
            flag = true;
        }
        catch (Exception ex) {
            LOGGER.log(Level.WARNING, ex.getMessage());
        }
        return new ResponseEntity<>(flag, HttpStatus.OK);
    }

    /** CHECK NEW PASSWORD EQUALS CURRENT PASSWORD **/
    @PutMapping(value = {AppActionController.PASSWORD_USING})
    public ResponseEntity<Boolean> passwordUsing(@RequestBody @NotNull @NotEmpty String password) {
        AppUser appUser = (AppUser)this.httpSession.getAttribute(AppAttribute.SIGN_INNED_USER);
        return new ResponseEntity<>(this.appUserService.passwordUsing(appUser, password), HttpStatus.OK);
    }

    /**
     *  CHANGE PASSWORD
     */
    @PutMapping(value = {AppActionController.CHANGE_PASSWORD})
    public ResponseEntity<Boolean> changePassword(@RequestBody @NotNull @NotEmpty String password) {
        AppUser appUser = (AppUser)this.httpSession.getAttribute(AppAttribute.SIGN_INNED_USER);
        boolean flag = false;
        try {
            this.appUserService.changePassword(appUser, password);
            flag = true;
        }
        catch (Exception ex) {
            LOGGER.log(Level.WARNING, ex.getMessage());
        }
        return new ResponseEntity<>(flag, HttpStatus.OK);
    }

    /**
     *  EDIT APP USER PROFILE
     */
    @PutMapping(value = {AppActionController.UPDATE_APP_USER})
    public ResponseEntity<Boolean> updateAppUser(@RequestBody @NotNull AppUser appUser) {
        boolean flag = false;
        AppUserProfile appUserProfile = this.appUserProfileService.updateAppUserProfile(appUser.getAppUserProfile());
        if(appUserProfile != null) {
            this.httpSession.setAttribute(AppAttribute.SIGN_INNED_USER, appUserProfile.getAppUser());
            flag = true;
        }
        return new ResponseEntity<>(flag, HttpStatus.OK);
    }

}