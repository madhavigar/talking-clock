package com.code.talkingclock.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumericTimeValidator{

	public static String TIME_REGEX = "^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$";
	public static Pattern pattern = Pattern.compile(TIME_REGEX);

	public  boolean isValid(String time) {

		Matcher macther = pattern.matcher(time);
		return macther.find();
	}

}
