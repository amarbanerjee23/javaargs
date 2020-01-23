package com.argument.marshaller;

import java.util.Iterator;

import com.argument.exception.ArgumentException;

public class BooleanArgumentMarshaler implements IArgumentMarshaler {
	
	private boolean booleanArgValue = false;

	public static boolean getBooleanValue(IArgumentMarshaler argMarshaller) {
		
		boolean booleanValue = false;
		
		if (argMarshaller instanceof BooleanArgumentMarshaler) {
			booleanValue = ((BooleanArgumentMarshaler) argMarshaller).booleanArgValue;
		}
		
		return booleanValue;
	}

	public void setArgument(Iterator<String> currentArgument) throws ArgumentException {
		booleanArgValue = true;
	}
}
