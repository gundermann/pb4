package com.example.helper;

import java.io.IOException;
import java.util.HashMap;

public class Strings {

	private static HashMap<String, String> stringMap = new HashMap<String, String>();

	public static String getString(String key) throws StringNotFoundException {
		if (stringMap.isEmpty())
			initStringMap();
		if(stringMap.keySet().contains(key))
			return stringMap.get(key);
		else
			throw new StringNotFoundException();
	}

	private static void initStringMap() {
		try {
			stringMap = new StringLoader().ladeStrings();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
