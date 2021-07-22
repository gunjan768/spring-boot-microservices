package com.gunjan768.business;

import com.gunjan768.data.api.TodoService;
import com.gunjan768.data.stub.TodoServiceStub;
import org.junit.jupiter.api.Test;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class TodoBusinessImplStubTest {

	@Test
	public void usingAStub() {
		TodoService todoService = new TodoServiceStub();
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);
		List<String> todos = todoBusinessImpl.retrieveTodosRelatedToSpring("not_used_anywhere_user");
		assertEquals(2, todos.size());
	}
}
