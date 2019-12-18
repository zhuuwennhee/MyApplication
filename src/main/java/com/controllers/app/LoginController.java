package com.controllers.app;

import com.consts.AppURI;
import com.consts.AppView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping(value = {AppURI.LOGIN})
    public String visitLogin() {
        return AppView.LOGIN;
    }

}