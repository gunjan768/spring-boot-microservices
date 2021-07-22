//package com.gunjan768.powermock;
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.powermock.api.mockito.PowerMockito;
//import org.powermock.core.classloader.annotations.PrepareForTest;
//import org.powermock.modules.junit4.PowerMockRunner;
//
//// In static method, we need to give the class, which contains the static method but for constructor, we need to
//// give class which is overwriting the constructor or making use of constructor and here SystemUnderTest class makes
//// use of constructor.
//
//@RunWith(PowerMockRunner.class)
///*To be able to mock the Constructor, we need to add in the Class that creates the new object*/
//@PrepareForTest({SystemUnderTest.class})
//public class PowerMockitoMockingConstructorTest {
//
//	private static final int SOME_DUMMY_SIZE = 100;
//
//	@Mock
//	Dependency dependencyMock;
//
//	@InjectMocks
//	SystemUnderTest systemUnderTest;
//
//	@Test
//	public void powerMockito_MockingAConstructor() throws Exception {
//
//		ArrayList<String> mockList = mock(ArrayList.class);
//
//		when(mockList.size()).thenReturn(SOME_DUMMY_SIZE);
//
//		PowerMockito.whenNew(ArrayList.class).withAnyArguments().thenReturn(mockList);
//
//		int size = systemUnderTest.methodUsingAnArrayListConstructor();
//
//		assertEquals(SOME_DUMMY_SIZE, size);
//	}
//}