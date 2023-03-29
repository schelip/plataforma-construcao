package com.equipe05.plataformaconstrucao.controller;


import com.equipe05.plataformaconstrucao.model.Game;
import com.equipe05.plataformaconstrucao.repository.GameRepository;
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
public class GameController {
    @Autowired
    GameRepository gameRepository;

    @GetMapping("/public/game")
    public ResponseEntity<List<Game>> getAllGame(@RequestParam(required = false) String name) {
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
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/game/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Game> getGameById(@PathVariable("id") long id) {
        Optional<Game> gameData = gameRepository.findById(id);

        return gameData.map(game -> new ResponseEntity<>(game, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/game")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Game> createGame(@RequestBody Game game) {
       try {
        Game _game = gameRepository.save(game);
           return new ResponseEntity<>(_game, HttpStatus.CREATED);
       } catch (Exception e) {
           return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    @PutMapping("/game/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Game> updateGame (@PathVariable("id") long id, @RequestBody Game game) {
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
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> deleteGame (@PathVariable("id") long id){
        try {
            gameRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/game")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> deleteAllGame() {
        try{
            gameRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
