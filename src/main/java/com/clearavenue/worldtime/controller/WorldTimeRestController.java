package com.clearavenue.worldtime.controller;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WorldTimeRestController {

	@RequestMapping(value = { "/getWorldTime", "/getWorldTime/{timezone}" }, method = RequestMethod.GET)
	public String getWorldTime(final @PathVariable Optional<String> timezone) {
		String result = StringUtils.EMPTY;

		if (timezone.isPresent()) {
			ZonedDateTime now = ZonedDateTime.now(ZoneId.of(timezone.get(), ZoneId.SHORT_IDS));
			result = DateTimeFormatter.ISO_ZONED_DATE_TIME.format(now);

			// uncomment next line to test SPOTBUGS
			// result = result;
		} else {
			ZonedDateTime now = ZonedDateTime.now();
			result = DateTimeFormatter.ISO_ZONED_DATE_TIME.format(now);
		}

		return result;
	}

}
