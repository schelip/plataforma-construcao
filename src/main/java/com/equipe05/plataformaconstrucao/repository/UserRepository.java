package com.equipe05.plataformaconstrucao.repository;

import com.equipe05.plataformaconstrucao.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByNickname(String nickname);

    List<User> findByEmailContaining(String email);

}

