package com.gunjan768;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class StringHelperParameterizedTest {

	// AACD => CD ACD => CD CDEF=>CDEF CDAA => CDAA

	StringHelper helper = new StringHelper();

	@ParameterizedTest
//	@ValueSource()
	@CsvSource({"AACD,CD", "ACD,CD"})
	// @CsvSource(value = {"AACD:CD", "ACD:CD"}, delimiter = ':')	// delimiter used is colon
	public void testTruncateAInFirst2Positions(String input, String expected) {
		assertEquals(expected,
				helper.truncateAInFirst2Positions(input));
	}
}
