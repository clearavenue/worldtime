package com.clearavenue.worldtime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class WorldTimeApplication extends SpringBootServletInitializer {

	public static void main(final String[] args) {
		SpringApplication.run(WorldTimeApplication.class, args);
	}

}

