package com.assignment.bitbuy;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.assignment.bitbuy.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import lombok.Getter;
import lombok.Setter;

@SpringBootTest
@AutoConfigureMockMvc
class BitbuyApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext context; 

	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	/*
	 * Below method test the api/create and api/login methods
	 */

	@org.junit.jupiter.api.Test
	public void testcreateAndLogin() throws Exception {

		User testUserObj = new User();
		testUserObj.setUserName("joshman");
		testUserObj.setPassword("Pass_1234");
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(testUserObj);
		
		this.mockMvc.perform(post("/api/create").contentType(APPLICATION_JSON_UTF8).content(requestJson))
				.andExpect(status().isCreated());

		this.mockMvc.perform(post("/api/login").contentType(APPLICATION_JSON_UTF8).content(requestJson))
				.andExpect(status().isOk());

	}

	@org.junit.jupiter.api.Test
	public void testSecuredGetAndPost() throws Exception {

		User testUserObj = new User();
		testUserObj.setUserName("testname");
		testUserObj.setPassword("pass100");
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		
		// Get: should give 401 because the request us sent without Basic-Auth creds
		  this.mockMvc.perform(get("/api/users/1")).andDo(print()).andExpect(status().
		  is4xxClientError());
		  
		// Post: should give 401 because the request us sent without Basic-Auth creds
		  this.mockMvc.perform(post("/api/users/1")).andDo(print()).andExpect(status().
				  is4xxClientError());
		 

		
	
	}

}
