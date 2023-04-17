package com.equipe05.plataformaconstrucao.repository;

import com.equipe05.plataformaconstrucao.model.skills.Skill;
import com.equipe05.plataformaconstrucao.model.skills.SkillCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
    List<Skill> findBySkillCategory(SkillCategory skillCategory);
}
