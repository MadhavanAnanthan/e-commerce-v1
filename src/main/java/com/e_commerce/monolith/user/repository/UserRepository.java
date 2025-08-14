package com.e_commerce.monolith.user.repository;

import com.e_commerce.monolith.user.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByEmail(String userSignedUpEmail);
}
