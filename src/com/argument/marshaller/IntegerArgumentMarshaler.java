package com.argument.marshaller;

import java.util.Iterator;
import java.util.NoSuchElementException;

import com.argument.exception.ArgumentException;

import static com.argument.exception.ArgumentException.ErrorCode;

public class IntegerArgumentMarshaler implements IArgumentMarshaler {
	
	private int intValue = 0;
	
	public static int getValue(IArgumentMarshaler marshaller) {
		
		int integerArgValue = 0;
		
		if (marshaller instanceof IntegerArgumentMarshaler) {
			integerArgValue = ((IntegerArgumentMarshaler) marshaller).intValue;
		}
		
		return integerArgValue;
	}

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
}
