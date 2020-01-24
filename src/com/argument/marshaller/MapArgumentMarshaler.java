package com.argument.marshaller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

import com.argument.exception.ArgumentException;

import static com.argument.exception.ArgumentException.ErrorCode;

public class MapArgumentMarshaler implements IArgumentMarshaler {
	
	private Map<String, String> mapValues = new HashMap<>();
	
	public static Map<String, String> getValue(IArgumentMarshaler am) {
		
		Map<String, String> mapArgValues = new HashMap<>();
		
		if (am instanceof MapArgumentMarshaler) {
			mapArgValues = ((MapArgumentMarshaler) am).mapValues;
		}
		
		return mapArgValues;
	}

	public void setArgument(Iterator<String> currentArgument) throws ArgumentException {
		try {
		
			String[] mapEntries = currentArgument.next().split(",");
			
			for (String entry : mapEntries) {
				
				String[] entryComponents = entry.split(":");
			
				if (entryComponents.length != 2) {
					throw new ArgumentException(ErrorCode.MALFORMED_MAP);
				} else {
					mapValues.put(entryComponents[0], entryComponents[1]);
				}
			}
		} catch (NoSuchElementException e) {
			throw new ArgumentException(ErrorCode.MISSING_MAP);
		}
	}
}
