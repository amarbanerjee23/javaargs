package com.argument.test;

import junit.framework.TestCase;

import com.argument.exception.ArgumentException;

import static com.argument.exception.ArgumentException.ErrorCode;

public class ArgumentsExceptionMessageTest extends TestCase {
	public void testErrorMessageForUnexpectedArgException() throws Exception {
		ArgumentException e = new ArgumentException(ErrorCode.UNEXPECTED_ARGUMENT, 'x', null);
		assertEquals("Argument -x unexpected.", e.getErrorMessage());
	}

	public void testErrorMessageForMissingStringArgumentException() throws Exception {
		ArgumentException e = new ArgumentException(ErrorCode.MISSING_STRING, 'x', null);
		assertEquals("Could not find string parameter for -x.", e.getErrorMessage());
	}

	public void testErrorMessageForInvalidIntegerException() throws Exception {
		ArgumentException e = new ArgumentException(ErrorCode.INVALID_INTEGER, 'x', "Forty two");
		assertEquals("Argument -x expects an integer but was 'Forty two'.", e.getErrorMessage());
	}

	public void testErrorMessageForMissingIntegerMessageException() throws Exception {
		ArgumentException e = new ArgumentException(ErrorCode.MISSING_INTEGER, 'x', null);
		assertEquals("Could not find integer parameter for -x.", e.getErrorMessage());
	}

	public void testErrorMessageForInvalidDoubleMessageException() throws Exception {
		ArgumentException e = new ArgumentException(ErrorCode.INVALID_DOUBLE, 'x', "Forty two");
		assertEquals("Argument -x expects a double but was 'Forty two'.", e.getErrorMessage());
	}

	public void testErrorMessageForMissingDoubleMessageException() throws Exception {
		ArgumentException e = new ArgumentException(ErrorCode.MISSING_DOUBLE, 'x', null);
		assertEquals("Could not find double parameter for -x.", e.getErrorMessage());
	}

	public void testErrorMessageForMissingMapMessageException() throws Exception {
		ArgumentException e = new ArgumentException(ErrorCode.MISSING_MAP, 'x', null);
		assertEquals("Could not find map string for -x.", e.getErrorMessage());
	}

	public void testErrorMessageForMalformedMapMessageException() throws Exception {
		ArgumentException e = new ArgumentException(ErrorCode.MALFORMED_MAP, 'x', null);
		assertEquals("Map string for -x is not of form k1:v1,k2:v2...", e.getErrorMessage());
	}  

	public void testErrorMessageForInvalidArgumentNameException() throws Exception {
		ArgumentException e = new ArgumentException(ErrorCode.INVALID_ARGUMENT_NAME, '#', null);
		assertEquals("'#' is not a valid argument name.", e.getErrorMessage());
	}

	public void testErrorMessageForInvalidFormatException() throws Exception {
		ArgumentException e = new ArgumentException(ErrorCode.INVALID_ARGUMENT_FORMAT, 'x', "$");
		assertEquals("'$' is not a valid argument format.", e.getErrorMessage());
	}
}
