package com.argument.marshaller;

import java.util.Iterator;

import com.argument.exception.ArgumentException;

public class BooleanArgumentMarshaler implements IArgumentMarshaler {
	
	private boolean booleanArgValue = false;

	public static boolean getBooleanValue(IArgumentMarshaler marshaller) {
		
		boolean booleanValue = false;
		
		if (marshaller instanceof BooleanArgumentMarshaler) {
			booleanValue = ((BooleanArgumentMarshaler) marshaller).booleanArgValue;
		}
		
		return booleanValue;
	}

	public void setArgument(Iterator<String> currentArgument) throws ArgumentException {
		booleanArgValue = true;
	}
}
