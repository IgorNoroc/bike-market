package com.igornoroc.auth.service;

import com.igornoroc.auth.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User saveOrUpdate(User user);

    User find(Long id);

    void delete(Long id);
}
