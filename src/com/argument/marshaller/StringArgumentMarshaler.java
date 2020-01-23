package com.argument.marshaller;

import static com.argument.exception.ArgumentException.ErrorCode.MISSING_STRING;

import java.util.Iterator;
import java.util.NoSuchElementException;

import com.argument.exception.ArgumentException;

public class StringArgumentMarshaler implements IArgumentMarshaler {
	
	private String stringValue = "";
	
	public static String getValue(IArgumentMarshaler am) {
		
		String stringValue = "";
		
		if (am instanceof StringArgumentMarshaler) {
			stringValue = ((StringArgumentMarshaler) am).stringValue;
		}
		
		return stringValue;
	}

	public void setArgument(Iterator<String> currentArgument) throws ArgumentException {
		try {
			stringValue = currentArgument.next();
		} catch (NoSuchElementException e) {
			throw new ArgumentException(MISSING_STRING);
		}
	}
}
