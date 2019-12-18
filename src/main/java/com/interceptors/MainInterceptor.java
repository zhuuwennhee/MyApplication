package com.interceptors;

import com.consts.AppURI;
import com.supports.WebSupportService;
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

    private List<String> appURIList;

    public MainInterceptor() {
        super();
        this.appURIList = new ArrayList<>();
        this.appURIList.add(AppURI.ROOT);
        this.appURIList.add(AppURI.INDEX);
        this.appURIList.add(AppURI.LOGIN);
        this.appURIList.add(AppURI.PROFILE);
        this.appURIList.add(AppURI.CONFIRM_REGISTRATION);
        this.appURIList.add(AppURI.UNLOCK_APP_USER);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        if(this.appURIList.contains(requestURI)) {
            Principal principal = request.getUserPrincipal();
            /**
             *  IF SIGN-INNED && REQUEST /app/login.html -> REDIRECT TO /user/profile.html
             */
            if(principal != null && AppURI.LOGIN.equals(requestURI)) {
                response.sendRedirect(request.getServletContext().getContextPath() + AppURI.PROFILE);
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