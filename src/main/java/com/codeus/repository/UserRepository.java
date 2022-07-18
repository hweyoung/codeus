package com.codeus.repository;

import com.codeus.domain.User;
import com.codeus.dto.user.request.CheckId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(String id);

    Optional<Long> countById(String id);
}
