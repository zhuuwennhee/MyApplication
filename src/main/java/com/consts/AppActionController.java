package com.consts;

public interface AppActionController {

    String GET_SIGN_INNED_APP_USER = "/GetSignInnedAppUser";

    String SIGN_IN_APP_USER = "/SignInAppUser";

    String APP_USER_NAME_EXISTS = "/AppUsernameExists";

    String EMAIL_ADDRESS_USED = "/EmailAddressUsed";

    String CREATE_APP_USER = "/CreateAppUser";

    String FIND_EMAIL_ADDRESS_BY_APP_USERNAME = "/FindEmailAddressByAppUsername";

    String RESEND_CONFIRMATION_TOKEN = "/ResendConfirmationToken";

    String SEND_EMAIL_UNLOCK_APP_USER = "/SendEmailUnlockAppUser";

    String CONFIRM_REGISTRATION = "/ConfirmRegistration/{appUserTokenValue}";

    String UNLOCK_APP_USER = "/UnlockAppUser/{appUserTokenValue}";

    String SEND_EMAIL_FORGOT_PASSWORD = "/SendEmailForgotPassword";

    String PASSWORD_USING = "/PasswordUsing";

    String CHANGE_PASSWORD = "/ChangePassword";

    String UPDATE_APP_USER = "/UpdateAppUser";

}