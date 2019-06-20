package com.clearavenue.worldtime.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.time.zone.ZoneRulesException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.NestedServletException;

import com.clearavenue.worldtime.WorldTimeApplication;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = { WorldTimeApplication.class }, webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class WorldTimeRestControllerTests {

	final ObjectMapper mapper = new ObjectMapper();

	@InjectMocks
	WorldTimeRestController controller;

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void getWorldTimeDefault() throws Exception {
		final MvcResult result = mockMvc.perform(get("/getWorldTime")).andExpect(status().isOk()).andReturn();
		String actual = result.getResponse().getContentAsString();
		assertNotNull(actual, "returned submissions should not be null");
	}

	@Test
	public void getWorldTimeParis() throws Exception {
		final MvcResult result = mockMvc.perform(get("/getWorldTime/ECT")).andExpect(status().isOk()).andReturn();
		String actual = result.getResponse().getContentAsString();
		assertNotNull(actual, "returned submissions should not be null");
		assertTrue(actual.contains("Europe/Paris"), actual);
	}

	@Test
	public void getWorldTimeUnknown() throws Exception {
		try {
			mockMvc.perform(get("/getWorldTime/BIL")).andExpect(status().isOk()).andReturn();
			fail("Should have failed with ZoneRulesException");
		} catch (NestedServletException nse) {
			assertEquals(ZoneRulesException.class, nse.getCause().getClass());
		}
	}

}
