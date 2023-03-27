package com.equipe05.plataformaconstrucao.repository;

import com.equipe05.plataformaconstrucao.model.ERole;
import com.equipe05.plataformaconstrucao.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
