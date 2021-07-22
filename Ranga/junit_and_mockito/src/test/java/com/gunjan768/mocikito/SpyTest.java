package com.gunjan768.mocikito;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

public class SpyTest {

	@Test
	public void creatingASpyOnArrayList() {
		// Creating a new array list ... is what spy() actually does. Plus you can overwrite the methods you want like
		// here we are overwriting size() method of list
		List<String> listSpy = spy(ArrayList.class);
		listSpy.add("Ranga");
		listSpy.add("in28Minutes");

		verify(listSpy).add("Ranga");
		verify(listSpy).add("in28Minutes");

		assertEquals(2, listSpy.size());
		assertEquals("Ranga", listSpy.get(0));
	}

	@Test
	public void creatingASpyOnArrayList_overridingSpecificMethods() {
		// Creating a new array list ... is what spy() actually does. Plus you can overwrite the methods you want like
		// here we are overwriting size() method of list
		List<String> listSpy = spy(ArrayList.class);
		listSpy.add("Ranga");
		listSpy.add("in28Minutes");

		when(listSpy.size()).thenReturn(-1);

		assertEquals(-1, listSpy.size());
		assertEquals("Ranga", listSpy.get(0));
	}
}