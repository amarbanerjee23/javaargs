package com.argument.marshaller;

import static com.argument.exception.ArgumentException.ErrorCode;

import java.util.*;

import com.argument.exception.ArgumentException;

public class DoubleArgumentMarshaler implements IArgumentMarshaler {
	public static double getValue(IArgumentMarshaler am) {
		
		double doubleArgValue = 0.0;
		
		if (am instanceof DoubleArgumentMarshaler) {
			doubleArgValue = ((DoubleArgumentMarshaler) am).doubleValue;
		}
		
		return doubleArgValue;
	}

	private double doubleValue = 0;

	public void setArgument(Iterator<String> currentArgument) throws ArgumentException {
		String parameter = null;
		
		try {
			parameter = currentArgument.next();
			doubleValue = Double.parseDouble(parameter);
		} catch (NoSuchElementException e) {
			throw new ArgumentException(ErrorCode.MISSING_DOUBLE);
		} catch (NumberFormatException e) {
			throw new ArgumentException(ErrorCode.INVALID_DOUBLE, parameter);
		}
	}
}
