package com.gunjan768.business;


import com.gunjan768.data.api.TodoService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import static org.hamcrest.CoreMatchers.is;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

public class TodoBusinessImplMockitoTest {

	@Test
	public void usingMockito() {
		TodoService todoService = mock(TodoService.class);

		List<String> allTodos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");

		// Dynamically stubing a method. Stubing means to create a static implementation of an interface
		when(todoService.retrieveTodos("user_not_used_anywhere")).thenReturn(allTodos);

		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);
		List<String> todos = todoBusinessImpl.retrieveTodosRelatedToSpring("user_not_used_anywhere");

		assertEquals(2, todos.size());
	}

	/**
	 * Behavior Driven Development (BDD) is a branch of Test Driven Development (TDD). BDD uses human-readable
	 * descriptions of software user requirements as the basis for software tests.
	 */

	@Test
	public void usingMockito_UsingBDD() {
		TodoService todoService = mock(TodoService.class);
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);
		List<String> allTodos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");

		//given
		given(todoService.retrieveTodos("user_not_used_anywhere")).willReturn(allTodos);

		//when
		List<String> todos = todoBusinessImpl.retrieveTodosRelatedToSpring("user_not_used_anywhere");

		//then
		assertThat(todos.size(), is(2));
	}

	@Test
	public void letsTestDeleteNow() {
		TodoService todoService = mock(TodoService.class);

		List<String> allTodos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");

		when(todoService.retrieveTodos("user_not_used_anywhere")).thenReturn(allTodos);

		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);

		todoBusinessImpl.deleteTodosNotRelatedToSpring("user_not_used_anywhere");

		verify(todoService).deleteTodo("Learn to Dance");

		// Never called as contains "Spring" word
		verify(todoService, never()).deleteTodo("Learn Spring MVC");
		verify(todoService, never()).deleteTodo("Learn Spring");

		verify(todoService, times(1)).deleteTodo("Learn to Dance");
		verify(todoService, atLeastOnce()).deleteTodo("Learn to Dance");
		verify(todoService, atLeast(1)).deleteTodo("Learn to Dance");

		// Using then and should i.e. BDD syntax
		then(todoService).should().deleteTodo("Learn to Dance");
		then(todoService).should(never()).deleteTodo("Learn Spring");
	}

	@Test
	public void captureArgument() {
		ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);

		TodoService todoService = mock(TodoService.class);

		List<String> allTodos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
		Mockito.when(todoService.retrieveTodos("user_not_used_anywhere")).thenReturn(allTodos);

		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);
		todoBusinessImpl.deleteTodosNotRelatedToSpring("user_not_used_anywhere");
		verify(todoService).deleteTodo(argumentCaptor.capture());

		assertEquals("Learn to Dance", argumentCaptor.getValue());
	}

	@Test
	public void captureArgument_using_BBD() {
		ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);

		TodoService todoService = mock(TodoService.class);

		List<String> allTodos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
		given(todoService.retrieveTodos("user_not_used_anywhere")).willReturn(allTodos);

		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);
		todoBusinessImpl.deleteTodosNotRelatedToSpring("user_not_used_anywhere");

		then(todoService).should().deleteTodo(argumentCaptor.capture());
		assertThat(argumentCaptor.getValue(), is("Learn to Dance"));
	}

	@Test
	public void captureArgument_using_BBD_2() {
		ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);

		TodoService todoService = mock(TodoService.class);

		List<String> allTodos = Arrays.asList("Learn to Rock and Roll", "Learn Spring", "Learn to Dance");
		given(todoService.retrieveTodos("user_not_used_anywhere")).willReturn(allTodos);

		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);
		todoBusinessImpl.deleteTodosNotRelatedToSpring("user_not_used_anywhere");

		then(todoService).should(times(2)).deleteTodo(argumentCaptor.capture());
		assertThat(argumentCaptor.getAllValues(), is(Arrays.asList("Learn to Rock and Roll", "Learn to Dance")));
		assertThat(argumentCaptor.getAllValues().size(), is(2));
	}
}
