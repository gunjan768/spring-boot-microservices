package com.gunjan768.restful.restful_demo.user;

import java.util.*;

import org.springframework.stereotype.Service;

@Service
public class UserDaoService {
	
	private static final List<User> users = new ArrayList<>();
	private static int usersCount = 3;

	static {
		users.addAll(
			Arrays.asList(
			new User(1, "Adam", new Date()),
			new User(2, "Eve", new Date()),
			new User(3, "Jack", new Date())
		));
	}

	public List<User> findAll() {
		return users;
	}

	public User save(User user) {

		if(user.getId() == null) {
			user.setId(++usersCount);
		}

		users.add(user);

		return user;
	}

	public User findOne(int id) {

		for(User user: users) {
			if(user.getId() == id) {
				return user;
			}
		}

		return null;
	}

	public User deleteById(int id) {

		Iterator<User> iterator = users.iterator();

		while(iterator.hasNext()) {
			User user = iterator.next();

			if (user.getId() == id) {
				iterator.remove();

				return user;
			}
		}

		return null;
	}
}