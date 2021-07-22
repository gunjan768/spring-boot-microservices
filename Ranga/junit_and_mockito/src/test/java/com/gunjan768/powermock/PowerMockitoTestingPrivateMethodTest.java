package com.gunjan768.powermock;

import static org.mockito.Mockito.when;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.powermock.reflect.Whitebox;

@ExtendWith({MockitoExtension.class})
public class PowerMockitoTestingPrivateMethodTest {

	@Mock
	Dependency dependencyMock;

	@InjectMocks
	SystemUnderTest systemUnderTest;

	// TODO: Check why PowerMock is not working
	@Test
	public void powerMockito_CallingAPrivateMethod() throws Exception, CloneNotSupportedException {
		assertThrows(Exception.class, () -> {
			when(dependencyMock.retrieveAllStats()).thenReturn(Arrays.asList(1, 2, 3));
			// For testing private methods, Whitebox class is used
			long value = Whitebox.invokeMethod(systemUnderTest, "privateMethodUnderTest");
			assertEquals(6, value);
		});
	}
}