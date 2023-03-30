package com.equipe05.plataformaconstrucao.controller;


import com.equipe05.plataformaconstrucao.model.User;
import com.equipe05.plataformaconstrucao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:5432")
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired UserRepository userRepository;

    @GetMapping("/user")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<User>> getAllUser(@RequestParam(required = false) String email) {
        try {
            List<User> user = new ArrayList<User>();

            if (email == null)
                user.addAll(userRepository.findAll());
            else
                user.addAll(userRepository.findByEmailContaining(email));
            if (user.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
        Optional<User> userData = userRepository.findById(id);

        return userData.map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/user")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ResponseEntity<User> createUser(@RequestBody User user) {
       try {
           User _user = userRepository.save(new User(
                   user.getUsername(),
                   user.getEmail(),
                   user.getPassword(),
                   user.getAge()));
           return new ResponseEntity<>(_user, HttpStatus.CREATED);
       } catch (Exception e) {
           return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    @PutMapping("/user/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        Optional<User> userData = userRepository.findById(id);

        if (userData.isPresent()) {
            User _user = userData.get();
            _user.setEmail(user.getEmail());
            _user.setUsername(user.getUsername());
            _user.setAge(user.getAge());
            _user.setPassword(user.getPassword());
            return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/user/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id){
        try {
            userRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/user")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<HttpStatus> deleteAllUser() {
        try{
            userRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/byUsername/{username}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<User> findByUsername(@PathVariable("username") String username) {
        try {
            Optional<User> users = userRepository.findByUsername(username);

            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(users.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
