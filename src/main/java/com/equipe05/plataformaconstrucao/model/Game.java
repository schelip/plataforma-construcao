package com.equipe05.plataformaconstrucao.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Objects;


@Entity
@Table(name = "game")
public class Game {
    private @Id @GeneratedValue Long id;
    private @NotBlank String name;
    private @NotBlank @Size(min = 7, max = 7) String colorHex;
    private @Lob @Basic(fetch = FetchType.LAZY) byte[] icon;
    private @Lob @Basic(fetch = FetchType.LAZY) byte[] backgroundImage;

    Game() {}

    public Game(String name, String colorHex, byte[] icon, byte[] backgroundImage) {
        this.name = name;
        this.colorHex = colorHex;
        this.icon = icon;
        this.backgroundImage = backgroundImage;
    }

    public Long getId() {
        return this.id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColorHex() {
        return colorHex;
    }

    public void setColorHex(String colorHex) {
        this.colorHex = colorHex;
    }

    public byte[] getIcon() {
        return icon;
    }

    public void setIcon(byte[] icon) {
        this.icon = icon;
    }

    public byte[] getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(byte[] backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Game game))
            return false;
        return Objects.equals(this.id, game.id) && Objects.equals(this.name, game.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name);
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", colorHex='" + colorHex + '\'' +
                '}';
    }

}
