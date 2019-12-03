package com.life.interceptors;

import com.life.consts.AppPublicPage;
import com.life.supports.WebSupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Component
public class MainInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private WebSupportService webSupportService;

    private List<String> publicPage;

    public MainInterceptor() {
        super();
        this.publicPage = new ArrayList<>();
        this.publicPage.add("/");
        this.publicPage.add(AppPublicPage.INDEX);
        this.publicPage.add(AppPublicPage.LOGIN);
        this.publicPage.add(AppPublicPage.PROFILE);
        this.publicPage.add(AppPublicPage.CONFIRM_REGISTRATION);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        if(this.publicPage.contains(requestURI)) {
            Principal principal = request.getUserPrincipal();
            /**
             *  IF SIGN-INNED && REQUEST /login.html -> REDIRECT TO /profile.html
             */
            if(principal != null && AppPublicPage.LOGIN.equals(requestURI)) {
                response.sendRedirect(request.getServletContext().getContextPath() + "/profile.html");
            }
            this.webSupportService.postVisitPublicPage(principal);
        }
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler);
    }

}