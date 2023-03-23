package com.equipe05.plataformaconstrucao.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "user_account",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = "username"),
            @UniqueConstraint(columnNames = "email"),
        })
public class User {
    private @Id @GeneratedValue Long id;
    private @NotBlank String username;
    private @NotBlank @Email String email;
    private @NotBlank String password;
    private @NotBlank @Min(18) int age;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
               joinColumns = @JoinColumn(name = "user_id"),
               inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    User() {}

    public User(Long id, String nickname, String email, String password, int age) {
        this.id = id;
        this.username = nickname;
        this.email = email;
        this.password = password;
        this.age = age;
    }

    public Long getId() {
        return this.id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String Email) {
        this.email = Email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String Password) {
        this.password = Password;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int Age) {
        this.age = Age;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof User user))
            return false;
        return Objects.equals(this.id, user.id) && Objects.equals(this.username, user.username)
                && Objects.equals(this.email, user.email) && Objects.equals(this.password, user.password)
                    && Objects.equals(this.age, user.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.username, this.email, this.password, this.age);
    }

    @Override
    public String toString() {
        return "User{" + "id=" + this.id + ", Nickname='" + this.username + '\'' + ", Email='" + this.email + '\'' +  ", Password='" + this.password + '\'' + ",'Age='" + this.age + '}';
    }

}
