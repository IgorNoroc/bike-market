package com.igornoroc.auth.rest;

import com.igornoroc.auth.config.jwt.JwtTokenProvider;
import com.igornoroc.auth.exceptions.JwtAuthenticationException;
import com.igornoroc.auth.model.User;
import com.igornoroc.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/save")
    public String save(@RequestBody User user) {
        userService.save(user);
        return String.format(
                "user %s was been saved", user.getEmail()
        );
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        UserDetails currentUser = userService.loadUserByUsername(user.getEmail());
        if (currentUser == null) {
            throw new JwtAuthenticationException("invalid email or password");
        }
        String token = jwtTokenProvider.createToken(user.getEmail(), user.getRoles());
        return ResponseEntity.ok(String.format(
                "user with email %s was been authentication , token : %s ", user.getEmail(), token));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/find_user/{id}")
    public User findUser(@PathVariable Long id) {
        return userService.find(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete_user/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return String.format(
                "user %s was been deleted", id
        );
    }
}
