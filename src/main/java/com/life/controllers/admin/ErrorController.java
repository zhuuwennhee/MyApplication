package com.life.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"/error"})
public class ErrorController {

	@GetMapping(value = {"/403.html"})
	public String visit403() {
		return "error/403";
	}

	@GetMapping(value = {"/404.html"})
	public String visit404() {
		return "error/404";
	}

	@GetMapping(value = {"/500.html"})
	public String visit500() {
		return "error/500";
	}

}