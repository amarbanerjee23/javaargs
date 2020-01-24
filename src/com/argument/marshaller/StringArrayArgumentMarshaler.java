package com.argument.marshaller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import com.argument.exception.ArgumentException;

import static com.argument.exception.ArgumentException.ErrorCode;

public class StringArrayArgumentMarshaler implements IArgumentMarshaler {

	private final List<String> stringArrayValueList = new ArrayList<>();
	
	public static String[] getValue(IArgumentMarshaler marshaller) {
		
		String[] strValue = new String[0];
		
		if (marshaller instanceof StringArrayArgumentMarshaler) {
			strValue = ((StringArrayArgumentMarshaler) marshaller).stringArrayValueList.toArray(new String[0]);
		}
		
		return strValue;
	}


	public void setArgument(Iterator<String> currentArgument) throws ArgumentException {
		try {
			stringArrayValueList.add(currentArgument.next());
		} catch (NoSuchElementException e) {
			throw new ArgumentException(ErrorCode.MISSING_STRING);
		}
	}
}
