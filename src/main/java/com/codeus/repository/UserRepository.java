package com.codeus.repository;

import com.codeus.domain.User;
import com.codeus.dto.user.request.CheckId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(String id);
    Optional<Long> countById(String id);
//     위와 같은 코드
//    @Query(value = "SELECT COUNT(u.id) FROM User u WHERE u.id = :userId GROUP BY u.id")
//    Optional<Long> countId(@Param("userId") String userId);
}
