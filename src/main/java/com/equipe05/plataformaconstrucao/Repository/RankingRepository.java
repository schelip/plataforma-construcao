package com.equipe05.plataformaconstrucao.Repository;

import com.equipe05.plataformaconstrucao.model.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RankingRepository extends JpaRepository<Ranking, Long> {
    List<Ranking> findByName(String name);

    List<Ranking> findByDescription(String description);
}
