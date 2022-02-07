package com.igornoroc.auth.service;

import com.igornoroc.auth.model.User;

public interface UserService {
    User saveOrUpdate(User user);

    User find(Long id);

    void delete(Long id);
}
