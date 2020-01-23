package com.argument.marshaller;

// java library imports
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import com.argument.exception.ArgumentException;

import static com.argument.exception.ArgumentException.ErrorCode;

public class StringArrayArgumentMarshaler implements ArgumentMarshaler {
	private final List<String> strings = new ArrayList<>();

	public void set(Iterator<String> currentArgument) throws ArgumentException {
		try {
			strings.add(currentArgument.next());
		} catch (NoSuchElementException e) {
			throw new ArgumentException(ErrorCode.MISSING_STRING);
		}
	}

	public static String[] getValue(ArgumentMarshaler am) {
		String[] strValue = new String[0];
		if (am instanceof StringArrayArgumentMarshaler) {
			strValue = ((StringArrayArgumentMarshaler) am).strings.toArray(new String[0]);
		}
		return strValue;
	}
}
