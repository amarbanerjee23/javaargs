package com.argument.marshaller;

import java.util.Iterator;
import java.util.NoSuchElementException;

import com.argument.exception.ArgumentException;

import static com.argument.exception.ArgumentException.ErrorCode;

public class IntegerArgumentMarshaler implements ArgumentMarshaler {
	private int intValue = 0;

	public void setArgument(Iterator<String> currentArgument) throws ArgumentException {
		String parameter = null;
		try {
			parameter = currentArgument.next();
			intValue = Integer.parseInt(parameter);
		} catch (NoSuchElementException e) {
			throw new ArgumentException(ErrorCode.MISSING_INTEGER);
		} catch (NumberFormatException e) {
			throw new ArgumentException(ErrorCode.INVALID_INTEGER, parameter);
		}
	}

	public static int getValue(ArgumentMarshaler am) {
		int integerArgValue = 0;
		if (am instanceof IntegerArgumentMarshaler) {
			integerArgValue = ((IntegerArgumentMarshaler) am).intValue;
		}
		return integerArgValue;
	}
}
