package com.equipe05.plataformaconstrucao.repository;

import com.equipe05.plataformaconstrucao.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByNickname(String nickname);

    Boolean existsByNickname(String nickname)

    List<User> findByEmailContaining(String email);

    Boolean existsByEmail(String email);
}

