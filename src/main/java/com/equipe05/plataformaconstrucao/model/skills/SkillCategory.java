package com.equipe05.plataformaconstrucao.model.skills;

import com.equipe05.plataformaconstrucao.model.Game;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "skill_category")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class SkillCategory {
    private @Id @GeneratedValue Long id;
    private @ManyToOne @JoinColumn(name = "game") @JsonIdentityReference(alwaysAsId = true) Game game;
    private @OneToMany(mappedBy = "skillCategory") List<Skill> skills;
    private String name;

    public SkillCategory() {
    }

    public SkillCategory(Game game, String name) {
        this.game = game;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SkillCategory that)) return false;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "SkillCategory{" +
                "id=" + id +
                ", game=" + game +
                ", name='" + name + '\'' +
                '}';
    }
}
