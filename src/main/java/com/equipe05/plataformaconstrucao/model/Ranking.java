package com.equipe05.plataformaconstrucao.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "ranking")
public class Ranking {

    private @Id
    @GeneratedValue long id;
    private String name;
    private int experience;
    private String description;

    public Ranking() {
    }

    public Ranking(long id, String name, int experience, String description) {
        this.id = id;
        this.name = name;
        this.experience = experience;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Ranking))
            return false;
        Ranking rank = (Ranking) o;
        return Objects.equals(this.id, rank.id) && Objects.equals(this.name, rank.name)
                && Objects.equals(this.experience, rank.experience) && Objects.equals(this.description, rank.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.experience, this.description);
    }

    @Override
    public String toString() {
        return "RAnking{" + "id=" + this.id + ", name='" + this.name + '\'' + ", experience='" + this.experience + '\'' +  ", Description='" + this.description +  '}';
    }


}