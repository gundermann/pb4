package com.example.helper;

public class StringNotFoundException extends Exception {

	private static final long serialVersionUID = 7978795246473644110L;

	@Override
	public String getMessage() {
		return "String ist nicht vorhanden";
	}

}
