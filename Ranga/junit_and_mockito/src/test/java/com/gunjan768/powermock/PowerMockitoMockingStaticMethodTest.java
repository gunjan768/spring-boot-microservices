//package com.gunjan768.powermock;
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.ArgumentMatchers.anyInt;
//import static org.mockito.Mockito.when;
//
//import java.util.Arrays;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.powermock.api.mockito.PowerMockito;
//import org.powermock.core.classloader.annotations.PrepareForTest;
//import org.powermock.modules.junit4.PowerMockRunner;
//
//@RunWith(PowerMockRunner.class)
///*The class with static method to be mocked*/
//@PrepareForTest({ UtilityClass.class })
//public class PowerMockitoMockingStaticMethodTest {
//
//	@Mock
//	Dependency dependencyMock;
//
//	@InjectMocks
//	SystemUnderTest systemUnderTest;
//
//	@Test
//	public void powerMockito_MockingAStaticMethodCall() {
//
//		when(dependencyMock.retrieveAllStats()).thenReturn(Arrays.asList(1, 2, 3));
//
//		PowerMockito.mockStatic(UtilityClass.class);
//
//		when(UtilityClass.staticMethod(anyInt())).thenReturn(150);
//
//		assertEquals(150, systemUnderTest.methodCallingAStaticMethod());
//
//		//To verify a specific method call
//		//First : Call PowerMockito.verifyStatic()
//		//Second : Call the method to be verified
//		PowerMockito.verifyStatic(UtilityClass.class);
//		UtilityClass.staticMethod(123232);
//	}
//}