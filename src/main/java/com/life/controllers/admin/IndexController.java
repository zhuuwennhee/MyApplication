package com.life.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    /**
     *  VISIT HOME
     */
    @GetMapping(value = {"/", "/index.html"})
    public String visitIndex() {
        return "/admin/index";
    }

}