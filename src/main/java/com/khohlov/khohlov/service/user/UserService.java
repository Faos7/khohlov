package com.khohlov.khohlov.service.user;

import com.khohlov.khohlov.domain.security.User;
import com.khohlov.khohlov.domain.security.UserCreateForm;

import java.util.Collection;
import java.util.Optional;

public interface UserService {

    Optional<User> getUserById(Long id);

    Optional<User> getUserByUsername(String username);

    Collection<User> getAllUsers();

    User create(UserCreateForm form);
}