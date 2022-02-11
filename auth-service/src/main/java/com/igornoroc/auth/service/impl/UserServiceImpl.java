package com.igornoroc.auth.service.impl;

import com.igornoroc.auth.exceptions.UserAlreadyExistsExceptions;
import com.igornoroc.auth.exceptions.UserNotFoundException;
import com.igornoroc.auth.model.User;
import com.igornoroc.auth.repository.UserRepo;
import com.igornoroc.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final BCryptPasswordEncoder encoder;

    @Override
    public User save(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        try {
            return userRepo.save(user);
        } catch (Exception e) {
            throw new UserAlreadyExistsExceptions();
        }
    }

    @Override
    public User find(Long id) {
        return userRepo.findById(id)
                .orElseThrow(
                        () -> new UserNotFoundException(
                                String.format("user %d not found!", id))
                );
    }

    private void update(User user) {
        User currentUser = userRepo.findByEmail(user.getEmail());
        if (currentUser != null) {
            userRepo.save(user);
        }
    }

    @Override
    public void delete(Long id) {
        userRepo.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException(
                    String.format("user %s not found!", email));
        }
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                new ArrayList<>());
    }
}
