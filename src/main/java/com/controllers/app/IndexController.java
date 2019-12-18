package com.controllers.app;

import com.consts.AppURI;
import com.consts.AppView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    /**
     *  VISIT HOME
     */
    @GetMapping(value = {AppURI.ROOT, AppURI.INDEX})
    public String visitIndex() {
        return AppView.INDEX;
    }

}