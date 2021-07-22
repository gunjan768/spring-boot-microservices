package com.gunjan768.data.api;

import java.util.List;

// External Service - Lets say this comes from WonderList (external website)
public interface TodoService {

	List<String> retrieveTodos(String user);

	void deleteTodo(String todo);

}