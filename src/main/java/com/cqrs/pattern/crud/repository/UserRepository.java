package com.cqrs.pattern.crud.repository;

import java.util.HashMap;
import java.util.Map;

import com.cqrs.pattern.domain.User;

public class UserRepository {

	private Map<String, User> store = new HashMap<>();

    public void addUser(String id, User user) {
        store.put(id, user);
    }

    public User getUser(String id) {
        return store.get(id);
    }
}
