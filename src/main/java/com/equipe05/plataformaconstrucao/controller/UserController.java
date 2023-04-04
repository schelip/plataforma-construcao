package com.equipe05.plataformaconstrucao.controller;


import com.equipe05.plataformaconstrucao.model.User;
import com.equipe05.plataformaconstrucao.repository.UserRepository;
import com.equipe05.plataformaconstrucao.services.AuthService;
import com.equipe05.plataformaconstrucao.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.text.MessageFormat;
import java.util.*;


@CrossOrigin(origins = "http://localhost:5432")
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired UserRepository userRepository;
    @Autowired UserService userService;
    @Autowired PasswordEncoder encoder;

    @GetMapping("/user")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
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

        if (userData.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        User user = userData.get();
        user.setPassword(null);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/user")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ResponseEntity<User> createUser(@RequestBody User user) {
       try {
           user.setPassword(encoder.encode(user.getPassword()));
           User _user = userRepository.save(user);
           return new ResponseEntity<>(_user, HttpStatus.CREATED);
       } catch (Exception e) {
           return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    @PutMapping("/user/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        if (!AuthService.isPrincipalModerator() && AuthService.getPrincipal().getId() != id) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        Optional<User> userData = userRepository.findById(id);

        if (userData.isPresent()) {
            User _user = userData.get();
            _user.setEmail(user.getEmail());
            _user.setUsername(user.getUsername());
            _user.setAge(user.getAge());
            _user.setPassword(encoder.encode(user.getPassword()));
            _user.setDescription(user.getDescription());
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
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
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

    @GetMapping("/user/{id}/profile-pic")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> getProfilePic(@PathVariable("id") Long id) {
        if (!AuthService.isPrincipalModerator() && AuthService.getPrincipal().getId() != id) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        try {
            byte[] bytes = userService.getProfilePic(id);
            String icon = Base64.getEncoder().encodeToString(bytes);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"icon-${System.currentTimeMillis()}\"")
                    .body(icon);
        } catch(NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/{id}/banner")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> getBanner(@PathVariable("id") Long id) {
        if (!AuthService.isPrincipalModerator() && AuthService.getPrincipal().getId() != id) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        try {
            byte[] icon = userService.getBanner(id);
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(MediaType.IMAGE_JPEG_VALUE))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"background-image-${System.currentTimeMillis()}\"")
                    .body(icon);
        } catch(NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/user/{id}/profile-pic", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<HttpStatus> setProfilePic(@PathVariable("id") Long id, @RequestParam MultipartFile file) {
        if (!AuthService.isPrincipalModerator() && AuthService.getPrincipal().getId() != id) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        try {
            userService.setProfilePic(id, file);
            return ResponseEntity.created(new URI(MessageFormat.format("/api/user/{0}/profile-pic", id))).build();
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/user/{id}/banner", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<HttpStatus> setBanner(@PathVariable("id") Long id, @RequestParam MultipartFile file) {
        try {
            if (!userRepository.existsById(id)) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            userService.setBanner(id, file);
            return ResponseEntity.created(new URI(MessageFormat.format("/api/user/{0}/banner", id))).build();
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
