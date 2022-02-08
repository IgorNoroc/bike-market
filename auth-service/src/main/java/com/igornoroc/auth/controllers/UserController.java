package com.igornoroc.auth.controllers;

import com.igornoroc.auth.model.User;
import com.igornoroc.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/save")
    public String save(@RequestBody User user) {
        userService.saveOrUpdate(user);
        return String.format(
                "user %s was been updated", user.getEmail()
        );
    }

    @GetMapping("/find_user/{id}")
    public User findUser(@PathVariable Long id) {
        return userService.find(id);
    }

    @DeleteMapping("/delete_user/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return String.format(
                "user %s was been deleted", id
        );
    }
}
