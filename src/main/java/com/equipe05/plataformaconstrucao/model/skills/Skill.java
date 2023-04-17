package com.equipe05.plataformaconstrucao.model.skills;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "skill")
public class Skill {
    private @Id @GeneratedValue Long id;
    private @NotBlank String name;
    private @ManyToOne @JoinColumn(name = "skill_category") @JsonIdentityReference(alwaysAsId = true) SkillCategory skillCategory;
    private Boolean isTracked = false;
    private String jsonPath;
    private @Lob @Basic(fetch = FetchType.LAZY) byte[] icon;

    public Skill() {
    }

    public Skill(String name, SkillCategory skillCategory, Boolean isTracked, String percentileJSON, byte[] icon) {
        this.name = name;
        this.skillCategory = skillCategory;
        this.isTracked = isTracked;
        this.jsonPath = percentileJSON;
        this.icon = icon;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SkillCategory getSkillCategory() {
        return skillCategory;
    }

    public void setSkillCategory(SkillCategory skillCategory) {
        this.skillCategory = skillCategory;
    }

    public Boolean getTracked() {
        return isTracked;
    }

    public void setTracked(Boolean tracked) {
        isTracked = tracked;
    }

    public String getJsonPath() {
        return jsonPath;
    }

    public void setJsonPath(String jsonPath) {
        this.jsonPath = jsonPath;
    }

    public byte[] getIcon() {
        return icon;
    }

    public void setIcon(byte[] icon) {
        this.icon = icon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Skill skill)) return false;
        return Objects.equals(getId(), skill.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Skill{" +
                "id=" + id +
                ", skillCategory=" + skillCategory +
                ", isTracked=" + isTracked +
                ", jsonPath='" + jsonPath + '\'' +
                ", icon=" + Arrays.toString(icon) +
                '}';
    }
}
