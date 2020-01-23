package com.argument.marshaller;

import java.util.Iterator;

import com.argument.exception.ArgumentException;

public interface IArgumentMarshaler {
	
	void setArgument(Iterator<String> currentArgument) throws ArgumentException;
	
}
 