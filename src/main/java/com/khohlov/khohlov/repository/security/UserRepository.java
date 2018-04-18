package com.khohlov.khohlov.repository.security;

import com.khohlov.khohlov.domain.security.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findOneByEmail(String email);
}
