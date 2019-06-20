package com.clearavenue.worldtime.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {

	@Value("${version}")
	private String version;

	/**
	 * Gets the app version.
	 *
	 * @return the version
	 */
	@RequestMapping(value = "/version", method = RequestMethod.GET)
	public String getVersion() {
		return version;
	}
}
