package com.code.talkingclock;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.code.talkingclock.service.ClockTimeConverterService;
import com.code.talkingclock.validators.NumericTimeValidator;

/***
 * This class takes any arbitrary Numeric Time parameter as input and return the
 * "Human Friendly Text" equivalent.
 * 
 * @author 91959
 *
 */

public class TalkingClockCommanLine {

	public static void main(String[] args) {

		String time = null;
		NumericTimeValidator validatore = new NumericTimeValidator();

		if (args.length <= 1) {
			if (args.length == 0)
				time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
			else {
				time = args[0];
			}
		}

		if (validatore.isValid(time)) {
			ClockTimeConverterService ckt = new ClockTimeConverterService();
			String timeInWords = ckt.convertTimeToText(time);
			System.out.println("talking-clock : " + timeInWords);

		} else {
			System.out.println("Invalid time enterd : " + time);
		}

	}

}
