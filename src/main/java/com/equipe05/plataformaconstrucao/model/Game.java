package com.equipe05.plataformaconstrucao.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.util.Objects;
import jakarta.persistence.Table;


@Entity
@Table(name = "game")
public class Game {
    private @Id @GeneratedValue Long id;
    private String nameGame;

    Game() {}

    public User(Long id, String nameGame) {
        this.id = id;
        this.nameGame = nameGame;
    }

    public Long getId() {
        return this.id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public String getNameGame() {
        return this.nameGame;
    }

    public void setNameGame(String nameGame) {
        this.nameGame = nameGame;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Game))
            return false;
            Game game = (Game) o;
        return Objects.equals(this.id, game.id) && Objects.equals(this.nameGame, game.nameGame);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.nameGame);
    }

    @Override
    public String toString() {
        return "User{" + "id=" + this.id + ", Nickname='" + this.nameGame + '}';
    }

}
