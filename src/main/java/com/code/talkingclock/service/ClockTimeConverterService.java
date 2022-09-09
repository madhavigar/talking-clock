package com.code.talkingclock.service;

import org.springframework.stereotype.Service;

/***
 * Class that converts numerical time into human readable text
 * 
 * @author 
 *
 */

@Service
public class ClockTimeConverterService {

	public static String[] Number_IN_WORDS = { " ", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight",
			"Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen",
			"Nineteen", "Twenty", "Twenty one", "Twenty two", "Twenty three", "Twenty four", "Twenty five",
			"Twenty six", "Twenty seven", "Twenty eight", "Twenty nine" };
	public static String PAST = "past";
	public static String TO = "to";

	public String convertTimeToText(String time) {

		String resutlt;

		short hours = Short.valueOf(time.substring(0, 2));
		short mins = Short.valueOf(time.substring(3, 5));

		int h = hours > 12 ? hours - 12 : hours;

		if (mins == 0) {
			String hourText = Number_IN_WORDS[h];

			resutlt = hourText.concat(" o'clock");

		} else {
			String hourText = Number_IN_WORDS[h].toLowerCase();

			if (mins == 15) {
				
				resutlt = String.join(Number_IN_WORDS[0], "Quarter", PAST, hourText);
			} else if (mins == 30) {
				
				resutlt = String.join(Number_IN_WORDS[0], "Half", PAST, hourText);
			} else if (mins == 45) {
				
				hourText = Number_IN_WORDS[h + 1].toLowerCase();
				resutlt = String.join(Number_IN_WORDS[0], "Quarter", TO, hourText);
			}

			else if (mins < 30) {
				resutlt = String.join(Number_IN_WORDS[0], Number_IN_WORDS[mins], "past", hourText);

			} else {
				hourText = Number_IN_WORDS[h + 1].toLowerCase();

				resutlt = String.join(Number_IN_WORDS[0], Number_IN_WORDS[60 - mins], "to", hourText);
			}
		}

		return resutlt;

	}

}
