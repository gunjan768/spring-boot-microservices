package com.gunjan768.data.stub;

import com.gunjan768.data.api.TodoService;

import java.util.Arrays;
import java.util.List;


public class TodoServiceStub implements TodoService {
	public List<String> retrieveTodos(String user) {
		return Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
	}

	public void deleteTodo(String todo) {

	}
}