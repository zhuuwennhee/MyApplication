package com.life.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping(value = {"/login.html"})
    public String visitLogin() {
        return "/admin/login";
    }

}