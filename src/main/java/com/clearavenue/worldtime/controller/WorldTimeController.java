package com.clearavenue.worldtime.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WorldTimeController {

	@GetMapping("/")
	public String index(final ModelMap model) {
		return "index";
	}

	@GetMapping("/getTime")
	public String getTime(final ModelMap model) {
		return "getTime";
	}

	@GetMapping(path = "/logout")
	public String logout(HttpServletRequest request) throws ServletException {
		request.logout();
		return "redirect:https://keycloak.devsecops.clearavenue.com/auth/realms/clearavenue/protocol/openid-connect/logout?redirect_uri=https://worldtime.devsecops.clearavenue.com";
	}
}
