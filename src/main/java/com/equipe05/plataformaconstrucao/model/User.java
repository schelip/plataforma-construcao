package com.equipe05.plataformaconstrucao.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.util.Objects;
import jakarta.persistence.Table;


@Entity
@Table(name = "user_account")
public class User {
    private @Id @GeneratedValue Long id;
    private String nickname;
    private String email;
    private String password;
    private int age;

    User() {}

    public User(Long id, String nickname, String email, String password, int age) {
        this.id = id;
        this.nickname = nickname;
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

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof User))
            return false;
        User user = (User) o;
        return Objects.equals(this.id, user.id) && Objects.equals(this.nickname, user.nickname)
                && Objects.equals(this.email, user.email) && Objects.equals(this.password, user.password)
                    && Objects.equals(this.age, user.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.nickname, this.email, this.password, this.age);
    }

    @Override
    public String toString() {
        return "User{" + "id=" + this.id + ", Nickname='" + this.nickname + '\'' + ", Email='" + this.email + '\'' +  ", Password='" + this.password + '\'' + ",'Age='" + this.age + '}';
    }

}
