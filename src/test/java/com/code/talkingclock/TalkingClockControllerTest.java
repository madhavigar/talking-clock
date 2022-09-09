package com.code.talkingclock;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.code.talkingclock.model.ErrorResponse;
import com.code.talkingclock.service.ClockTimeConverterService;
import com.code.talkingclock.web.controller.TalkingClockController;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TalkingClockControllerTest {

	@InjectMocks
	private TalkingClockController talkingClockController;

	@Mock
	private ClockTimeConverterService clockTimeConverterService;

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	private String url;

	@BeforeEach
	public  void init() {
		url = "http://localhost:" + port + "/timeConverion/convert";
	}

	@Test
	public void testControllerMethod() {

		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		when(clockTimeConverterService.convertTimeToText(any(String.class))).thenReturn("Five past one");
		String result = talkingClockController.getNumericTimeInText("13:05");
		assertEquals(result, "Five past one");
	}

	@Test
	public void testHttpReqWthValidTime() {

		ResponseEntity<String> result = restTemplate.getForEntity(url + "?time=13:05", String.class);
		assertEquals(result.getBody(), "Five past one");
	}

	@Test
	public void testHttpReqWthoutTime() {

		restTemplate.getForEntity(url, String.class);

	}

	@Test
	public void testHttpReqWithInvalidTime() {

		ResponseEntity<ErrorResponse> result = restTemplate.getForEntity(url + "?time=24:05", ErrorResponse.class);
		assertEquals(result.getBody().getErrors().size(), 1);
		assertEquals(result.getStatusCode(), HttpStatus.BAD_REQUEST);
	}

	@Test
	public void testHttpReqWithInvalidTimeFormat() {

		ResponseEntity<ErrorResponse> result = restTemplate.getForEntity(url + "?time=24", ErrorResponse.class);
		assertEquals(result.getBody().getErrors().size(), 1);
		assertEquals(result.getStatusCode(), HttpStatus.BAD_REQUEST);
	}

}
