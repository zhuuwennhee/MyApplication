package com.life.configurations;

import com.life.consts.ExceptionMessage;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AppAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException) throws IOException, ServletException {
        String message = authenticationException.getMessage();
        switch(message) {
            case ExceptionMessage.USER_DISABLED: {
                this.setDefaultFailureUrl("/login.html?error=disabled");
                break;
            }
            case ExceptionMessage.USER_LOCKED: {
                this.setDefaultFailureUrl("/login.html?error=locked");
                break;
            }
            case ExceptionMessage.USER_NOT_FOUND:
            default:{
                this.setDefaultFailureUrl("/login.html?error=incorrect");
            }
        }
        super.onAuthenticationFailure(request, response, authenticationException);
    }

}