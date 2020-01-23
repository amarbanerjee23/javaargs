package com.argument.marshaller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

import com.argument.exception.ArgumentException;

import static com.argument.exception.ArgumentException.ErrorCode;

public class MapArgumentMarshaler implements ArgumentMarshaler {
	private Map<String, String> map = new HashMap<>();

	public void set(Iterator<String> currentArgument) throws ArgumentException {
		try {
			String[] mapEntries = currentArgument.next().split(",");
			for (String entry : mapEntries) {
				String[] entryComponents = entry.split(":");
				if (entryComponents.length != 2) {
					throw new ArgumentException(ErrorCode.MALFORMED_MAP);
				} else {
					map.put(entryComponents[0], entryComponents[1]);
				}
			}
		} catch (NoSuchElementException e) {
			throw new ArgumentException(ErrorCode.MISSING_MAP);
		}
	}

	public static Map<String, String> getValue(ArgumentMarshaler am) {
		Map<String, String> mapArgValue = new HashMap<>();
		if (am instanceof MapArgumentMarshaler) {
			mapArgValue = ((MapArgumentMarshaler) am).map;
		}
		return mapArgValue;
	}
}
