package com.controllers.app;

import com.consts.AppURI;
import com.consts.AppView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

	@GetMapping(value = {AppURI.ERROR_403})
	public String visit403() {
		return AppView.ERROR_403;
	}

	@GetMapping(value = {AppURI.ERROR_404})
	public String visit404() {
		return AppView.ERROR_404;
	}

	@GetMapping(value = {AppURI.ERROR_500})
	public String visit500() {
		return AppView.ERROR_500;
	}

}