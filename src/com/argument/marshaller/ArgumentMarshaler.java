package com.argument.marshaller;

import java.util.Iterator;

import com.argument.exception.ArgumentException;

public interface ArgumentMarshaler {
	void setArgument(Iterator<String> currentArgument) throws ArgumentException;
}
 