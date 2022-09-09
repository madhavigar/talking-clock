package com.code.talkingclock.web.controller;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.code.talkingclock.service.ClockTimeConverterService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Validated
@RequestMapping("/timeConverion")
@Api(tags = "ClockTimeConversion")

public class TalkingClockController {
	
	@Autowired
	private ClockTimeConverterService clockTickService;
	
	
	@RequestMapping(value="/convert", method = RequestMethod.GET,  produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Clock time in HH:mm format",
	 notes = "Return  human readable text for a given clock time "
	 , httpMethod = "GET"
	 , response = String.class
	 )
	
	public String getNumericTimeInText(@Valid @RequestParam(required = false) @Pattern(regexp="^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$", message ="Invalid time format.Expected format is HH:mm") String time) {
		
		if(Objects.isNull(time))
			time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
		String restult = clockTickService.convertTimeToText(time);		
		return restult;
		
	}

}
