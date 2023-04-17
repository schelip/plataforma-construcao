package com.equipe05.plataformaconstrucao.repository;

import com.equipe05.plataformaconstrucao.model.Game;
import com.equipe05.plataformaconstrucao.model.skills.SkillCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillCategoryRepository extends JpaRepository<SkillCategory, Long> {
    List<SkillCategory> findByGame(Game game);
}
