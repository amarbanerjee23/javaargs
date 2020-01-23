package com.argument.marshaller;

import java.util.Iterator;

import com.argument.exception.ArgumentException;

public class BooleanArgumentMarshaler implements ArgumentMarshaler {

	private boolean booleanArgValue = false;

	public void setArgument(Iterator<String> currentArgument) throws ArgumentException {
		booleanArgValue = true;
	}

	public static boolean getBooleanValue(ArgumentMarshaler argMarshaller) {
		boolean booleanValue = false;
		if (argMarshaller instanceof BooleanArgumentMarshaler) {
			booleanValue = ((BooleanArgumentMarshaler) argMarshaller).booleanArgValue;
		}
		return booleanValue;
	}
}
