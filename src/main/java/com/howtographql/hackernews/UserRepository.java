package com.howtographql.hackernews;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository {

    private final List<User> users;
    private static int ids = 0;

    public UserRepository() {
        this.users = new ArrayList<>();
        this.users.add(new User("1", "smellycat", "smelly@c.at", "meow"));
    }

    public Optional<User> findByEmail(String email) {
        return this.users.stream()
                .filter(u -> email.equals(u.getEmail()))
                .findFirst();
    }

    public Optional<User> findById(String id) {
        return this.users.stream()
                .filter(u -> id.equals(u.getId()))
                .findFirst();
    }

    public User saveUser(User user) {
        User newUser = new User(String.valueOf(++ids), user);
        users.add(newUser);
        return newUser;
    }
}
