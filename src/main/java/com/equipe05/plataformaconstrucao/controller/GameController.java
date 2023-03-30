package com.equipe05.plataformaconstrucao.controller;


import com.equipe05.plataformaconstrucao.model.Game;
import com.equipe05.plataformaconstrucao.repository.GameRepository;
import com.equipe05.plataformaconstrucao.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.text.MessageFormat;
import java.util.*;

@RestController
@RequestMapping("/api")
public class GameController {
    @Autowired GameRepository gameRepository;
    @Autowired GameService gameService;

    @GetMapping("/public/game")
    public ResponseEntity<List<Game>> getAll(@RequestParam(required = false) String name) {
        try {
            List<Game> games = new ArrayList<>();

            if (name == null) {
                games.addAll(gameRepository.findAll());
            }
            else
                games.addAll(gameRepository.findByName(name));
            if (games.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(games, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/game/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Game> getById(@PathVariable("id") long id) {
        Optional<Game> gameData = gameRepository.findById(id);

        return gameData.map(game -> new ResponseEntity<>(game, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/game")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Game> create(@RequestBody Game game) {
       try {
        Game _game = gameRepository.save(game);
           return new ResponseEntity<>(_game, HttpStatus.CREATED);
       } catch (Exception e) {
           e.printStackTrace();
           return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    @PutMapping("/game/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Game> update(@PathVariable("id") long id, @RequestBody Game game) {
        Optional<Game> gameData = gameRepository.findById(id);

        if (gameData.isPresent()) {
            Game _game = gameData.get();
            _game.setName(game.getName());
            return new ResponseEntity<>(gameRepository.save(_game), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/game/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id){
        try {
            gameRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/game")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<HttpStatus> deleteAll() {
        try{
            gameRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/public/game/{id}/icon")
    public ResponseEntity<?> getIcon(@PathVariable("id") Long id) {
        try {
            byte[] bytes = gameService.getGameIcon(id);
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

    @GetMapping("/public/game/{id}/background-image")
    public ResponseEntity<?> getBackgroundImage(@PathVariable("id") Long id) {
        try {
            byte[] icon = gameService.getGameBackgroundImage(id);
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

    @PostMapping(path = "/game/{id}/icon", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<HttpStatus> setIcon(@PathVariable("id") Long id, @RequestParam MultipartFile file) {
        try {
            gameService.setGameIcon(id, file);
            return ResponseEntity.created(new URI(MessageFormat.format("/api/game/{0}/icon", id))).build();
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/game/{id}/background-image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<HttpStatus> setBackgroundImage(@PathVariable("id") Long id, @RequestParam MultipartFile file) {
        try {
            if (!gameRepository.existsById(id)) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            gameService.setGameBackgroundImage(id, file);
            return ResponseEntity.created(new URI(MessageFormat.format("/api/game/{0}/icon", id))).build();
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
