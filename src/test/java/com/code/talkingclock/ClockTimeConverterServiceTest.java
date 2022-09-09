package com.code.talkingclock;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.code.talkingclock.service.ClockTimeConverterService;


public class ClockTimeConverterServiceTest {
	/*
	 * 01:00 One o'clock 
02:00 Two o'clock 
13:00 One o'clock 
13:05 Five past one 
13:10 Ten past one 
13:25 Twenty five past one 
13:30 Half past one 
13:35 Twenty five to two 
13:55 Five to two 

*/
	private ClockTimeConverterService clockTickUtil;
	
	@BeforeEach
	public void init() {
		clockTickUtil = new ClockTimeConverterService();
	}
	
	@Test
	public void TwoOClock() {
		String time = clockTickUtil.convertTimeToText("02:00" );
		assertEquals(time, "Two o'clock" );
	}
	
	@Test
	public void fivePastOne() {
		String time = clockTickUtil.convertTimeToText("13:05" );
		assertEquals(time, "Five past one" );
	}
	
	@Test
	public void oneOClock() {
		String time = clockTickUtil.convertTimeToText("01:00" );
		assertEquals(time, "One o'clock" );
	}
	
	@Test
	public void tenPastOne() {
		String time = clockTickUtil.convertTimeToText("13:10" );
		assertEquals(time, "Ten past one" );
	}
	
	@Test
	public void halfPasOne() {
		String time = clockTickUtil.convertTimeToText("13:30" );
		assertEquals(time, "Half past one" );
	}
	
	@Test
	public void twentyFivePastOne() {
		String time = clockTickUtil.convertTimeToText("13:25" );
		assertEquals(time, "Twenty five past one" );
	}

	
	@Test
	public void fiveToTwo() {
		String time = clockTickUtil.convertTimeToText("13:55" );
		assertEquals(time, "Five to two" );
	}

}
