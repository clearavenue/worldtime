package com.clearavenue.worldtime.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import com.clearavenue.worldtime.WorldTimeApplication;

import lombok.extern.slf4j.Slf4j;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = { WorldTimeApplication.class }, webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Slf4j
public class VersionControllerTests {

	@InjectMocks
	VersionController controller;

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		mockMvc = webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void getVersionEndpoint() throws Exception {
		final MvcResult result = mockMvc.perform(get("/version")).andExpect(status().isOk()).andReturn();
		assertEquals(getVersion(), result.getResponse().getContentAsString(), "Should be the version");
	}

	@Test
	public void getVersionBadEndpoint() throws Exception {
		mockMvc.perform(get("/badendpoint")).andExpect(status().isNotFound());
		assertTrue(true, "should always be true");
	}

	private static String getVersion() {
		final ClassPathResource resource = new ClassPathResource("application.properties");
		final Properties mavenProperties = new Properties();
		try (InputStream inputStream = resource.getInputStream()) {
			mavenProperties.load(inputStream);
		} catch (final IOException e) {
			log.error("Cannot load properties: {}", e.getMessage());
		}

		final String version = (String) mavenProperties.get("version");
		return StringUtils.defaultIfBlank(version, "No version detected");
	}
}
