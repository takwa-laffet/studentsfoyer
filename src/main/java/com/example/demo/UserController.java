package com.example.demo;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private Map<Long, User> userDB = new HashMap<>();
    private Long idCounter = 1L;

    // CREATE
    @PostMapping
    public User createUser(@RequestBody User user) {
        user.setId(idCounter++);
        userDB.put(user.getId(), user);
        return user;
    }

    // READ ALL
    @GetMapping
    public Collection<User> getAllUsers() {
        return userDB.values();
    }

    // READ ONE
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userDB.get(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        User user = userDB.get(id);
        if (user != null) {
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            userDB.put(id, user);
        }
        return user;
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        userDB.remove(id);
        return "User deleted with id: " + id;
    }
}
