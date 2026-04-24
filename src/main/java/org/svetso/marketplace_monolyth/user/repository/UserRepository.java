package org.svetso.marketplace_monolyth.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.svetso.marketplace_monolyth.auth.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByKeycloakId(String keycloakId);
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
}