package com.ghouse.coronax.util;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class DataParserUtil {

	public long parseLong(String string) {
		if (string != null && !string.trim().isEmpty()) {
			return Long.parseLong(string);
		}
		return 0;
	}

	public double parseDouble(String string) {
		if (string != null && !string.trim().isEmpty()) {
			return Double.parseDouble(string);
		}
		return 0.0;
	}

	// extract string with only alphabets and numerics exclude string after special characters
	public String extractAlphaNumericString(String originalString) {
		List<Character> separators=Arrays.asList('(',')','[',']',',','.','/','\\');
		return separators
				    .stream()
				    .map(originalString::indexOf)
				    .filter(min -> min > 0).reduce(Integer::min)
                    .map(position -> originalString.substring(0, position))
				    .orElse(originalString);

	}
}
