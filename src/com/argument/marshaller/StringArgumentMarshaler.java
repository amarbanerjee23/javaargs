package com.argument.marshaller;

import static com.argument.exception.ArgumentException.ErrorCode.MISSING_STRING;

import java.util.Iterator;
import java.util.NoSuchElementException;

import com.argument.exception.ArgumentException;

public class StringArgumentMarshaler implements ArgumentMarshaler {
	private String stringValue = "";

	public void setArgument(Iterator<String> currentArgument) throws ArgumentException {
		try {
			stringValue = currentArgument.next();
		} catch (NoSuchElementException e) {
			throw new ArgumentException(MISSING_STRING);
		}
	}

	public static String getValue(ArgumentMarshaler am) {
		if (am != null && am instanceof StringArgumentMarshaler)
			return ((StringArgumentMarshaler) am).stringValue;
		else
			return "";
	}
}
