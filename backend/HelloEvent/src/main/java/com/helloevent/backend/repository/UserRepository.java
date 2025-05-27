package com.helloevent.backend.repository;

import com.helloevent.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);



    @Query(value = "select * from users where first_name = ?1 or email = ?1", nativeQuery = true)
    User getUserByUsernameOrEmail(String usernameFromToken);
}
