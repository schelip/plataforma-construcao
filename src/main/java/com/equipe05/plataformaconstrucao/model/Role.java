package com.equipe05.plataformaconstrucao.model;

import jakarta.persistence.*;

@Entity
@Table(name = "role")
public class Role {
    private @Id @GeneratedValue Long id;
    private @Enumerated(EnumType.STRING) @Column(length = 20) ERole name;

    Role() {}

    public Role(ERole name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ERole getName() {
        return name;
    }

    public void setName(ERole name) {
        this.name = name;
    }
}
