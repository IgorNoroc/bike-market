package com.igornoroc.auth.service.impl;

import com.igornoroc.auth.exceptions.UserNotFoundException;
import com.igornoroc.auth.model.User;
import com.igornoroc.auth.repository.UserRepo;
import com.igornoroc.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;

    @Override
    public User saveOrUpdate(User user) {
       return userRepo.save(user);
    }

    @Override
    public User find(Long id) {
        return userRepo.findById(id)
                .orElseThrow(
                        () -> new UserNotFoundException(
                                String.format("user %d not found!", id))
                );
    }

    @Override
    public void delete(Long id) {
        userRepo.deleteById(id);
    }
}
