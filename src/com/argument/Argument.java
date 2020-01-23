package com.argument;

//java static imports
import static com.argument.exception.ArgumentException.ErrorCode;

// local package imports
import com.argument.exception.ArgumentException;
import com.argument.marshaller.ArgumentMarshaler;
import com.argument.marshaller.BooleanArgumentMarshaler;
import com.argument.marshaller.DoubleArgumentMarshaler;
import com.argument.marshaller.IntegerArgumentMarshaler;
import com.argument.marshaller.MapArgumentMarshaler;
import com.argument.marshaller.StringArgumentMarshaler;
import com.argument.marshaller.StringArrayArgumentMarshaler;

// java library imports
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Argument {
	private Map<Character, ArgumentMarshaler> argMarshallerMap;
	private Set<Character> identfiedArguments;
	private ListIterator<String> currentArgument;

	public Argument(final String schema, final String[] args) throws ArgumentException {
		argMarshallerMap = new ConcurrentHashMap<>(addElementsInMarshallers(schema));
		identfiedArguments = new HashSet<>();
		parseArgumentStrings(Arrays.asList(args));
	}

	private void parseArgumentStrings(final List<String> argsList) throws ArgumentException {
		for (currentArgument = argsList.listIterator(); currentArgument.hasNext();) {
			final String argString = currentArgument.next();
			if (argString.startsWith("-")) {
				parseArgumentCharactersInString(argString.substring(1));
			} else {
				currentArgument.previous();
				break;
			}
		}
	}

	private void parseArgumentCharactersInString(final String argChars) throws ArgumentException {
		for (int i = 0; i < argChars.length(); i++) {
			parseIndividualArgumentCharacter(argChars.charAt(i));
		}
	}  
	
	private void parseIndividualArgumentCharacter(final char argChar) throws ArgumentException {
		final ArgumentMarshaler localMarshaller = argMarshallerMap.get(argChar);
		Optional<ArgumentMarshaler> localMarshallerExists = Optional.of(localMarshaller);
		if (localMarshallerExists.isEmpty()) {
			throw new ArgumentException(ErrorCode.UNEXPECTED_ARGUMENT, argChar, null);
		} else {
			identfiedArguments.add(argChar);
			try {
				localMarshallerExists.get().set(currentArgument);
			} catch (ArgumentException e) {
				e.setErrorArgumentId(argChar);
				throw e;
			}
		}
	}

	private Map<Character, ArgumentMarshaler> addElementsInMarshallers(final String schema) throws ArgumentException {
		final Map<Character, ArgumentMarshaler> argumentMarshallerPair = new ConcurrentHashMap<>();
		for (final String element : schema.split(",")) {
			if (element.length() > 0) {
				argumentMarshallerPair.putAll(populateMarshallers(element.trim()));
			}
		}
		return argumentMarshallerPair;
	}

	public boolean getBoolean(final char booleanArg) {
		return BooleanArgumentMarshaler.getValue(argMarshallerMap.get(booleanArg));
	}

	public double getDouble(final char doubleArg) {
		return DoubleArgumentMarshaler.getValue(argMarshallerMap.get(doubleArg));
	}

	public int getInt(final char charArg) {
		return IntegerArgumentMarshaler.getValue(argMarshallerMap.get(charArg));
	}

	public Map<String, String> getMap(final char mapArg) {
		return MapArgumentMarshaler.getValue(argMarshallerMap.get(mapArg));
	}

	public String getString(final char stringArg) {
		return StringArgumentMarshaler.getValue(argMarshallerMap.get(stringArg));
	}

	public String[] getStringArray(final char stringArrayArg) {
		return StringArrayArgumentMarshaler.getValue(argMarshallerMap.get(stringArrayArg));
	}

	public boolean hasArg(final char arg) {
		return identfiedArguments.contains(arg);
	}

	public int nextArgument() {
		return currentArgument.nextIndex();
	}

	private Map<Character, ArgumentMarshaler> populateMarshallers(String element) throws ArgumentException {
		final Map<Character, ArgumentMarshaler> localMarshalers = new HashMap<>();
		final char elementId = element.charAt(0);
		final String elementTail = element.substring(1);
		validateElementId(elementId);
		if (elementTail.length() == 0) {
			localMarshalers.put(elementId, new BooleanArgumentMarshaler());
		} else if (elementTail.equals("*")) {
			localMarshalers.put(elementId, new StringArgumentMarshaler());
		} else if (elementTail.equals("#")) {
			localMarshalers.put(elementId, new IntegerArgumentMarshaler());
		} else if (elementTail.equals("##")) {
			localMarshalers.put(elementId, new DoubleArgumentMarshaler());
		} else if (elementTail.equals("[*]")) {
			localMarshalers.put(elementId, new StringArrayArgumentMarshaler());
		} else if (elementTail.equals("&")) {
			localMarshalers.put(elementId, new MapArgumentMarshaler());
		} else {
			throw new ArgumentException(ErrorCode.INVALID_ARGUMENT_FORMAT, elementId, elementTail);
		}
		return localMarshalers;

	}

	private void validateElementId(final char elementId) throws ArgumentException {
		if (!Character.isLetter(elementId)) {
			throw new ArgumentException(ErrorCode.INVALID_ARGUMENT_NAME, elementId, null);
		}
	}
}