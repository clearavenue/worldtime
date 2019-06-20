package com.clearavenue.worldtime.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WorldTimeController {

	@GetMapping("/")
	public String index(final ModelMap model) {
		return "index";
	}
}
