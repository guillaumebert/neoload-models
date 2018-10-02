package com.neotys.neoload.model.converter;

import java.util.Optional;

import com.fasterxml.jackson.databind.util.StdConverter;
import com.google.common.base.Strings;

public final class StringToTimeDurationConverter extends StdConverter<String, Optional<Integer>> {
	private static final Optional<Integer> ERROR_VALUE = Optional.empty();

	@Override
	public Optional<Integer> convert(final String input) {
		if (Strings.isNullOrEmpty(input)) {
			return ERROR_VALUE;
		}
		
		try {
			Integer value = TimeDurationHelper.convertToInteger(input);
			if (value != null) {
				return Optional.of(Integer.valueOf(value));
			}
			return ERROR_VALUE;
		}
		catch(final Throwable exception) {
			return ERROR_VALUE;
		}		
	}
}
