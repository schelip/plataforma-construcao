package com.equipe05.plataformaconstrucao.repository;

import com.equipe05.plataformaconstrucao.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {
    List<Game> findByName(String name);
}
