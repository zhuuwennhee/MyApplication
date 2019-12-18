package com.configurations;

import com.consts.AppURI;
import com.consts.ExceptionMessage;
import com.consts.ResultStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException) throws IOException, ServletException {
        String message = authenticationException.getMessage();
        switch(message) {
            case ExceptionMessage.USER_DISABLED: {
                this.setDefaultFailureUrl(AppURI.LOGIN + "?ERROR=" + ResultStatus.DISABLED);
                break;
            }
            case ExceptionMessage.USER_LOCKED: {
                this.setDefaultFailureUrl(AppURI.LOGIN + "?ERROR=" + ResultStatus.LOCKED);
                break;
            }
            case ExceptionMessage.USER_NOT_FOUND:
            default:{
                this.setDefaultFailureUrl(AppURI.LOGIN + "?ERROR=" + ResultStatus.INCORRECT);
            }
        }
        super.onAuthenticationFailure(request, response, authenticationException);
    }

}