package com.clearavenue.worldtime.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FavIconController {

	@GetMapping("favicon.ico")
	String returnNoFavicon() {
		return "getTime";
	}
}