package com.equipe05.plataformaconstrucao.controller;


import com.equipe05.plataformaconstrucao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.equipe05.plataformaconstrucao.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:5432")
@RestController
@RequestMapping("/api")
public class GameController {
    @Autowired
    GameRepository gserRepository;

    @GetMapping("/game")
    public ResponseEntity<List<Game>> getAllGame(@RequestParam(required = false) String nameGame) {
        try {
            List<Game> game = new ArrayList<Game>();

            if (nameGame == null)
                gameRepository.findAll().forEach(game::add);
            else
                gameRepository.findByNameGame(game).forEach(game::add);
            if (game.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(game, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/game/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable("id") long id) {
        Optional<Game> gameData = userRepository.findById(id);

        if (gameData.isPresent()) {
            return new ResponseEntity<>(gameData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/game")
    public ResponseEntity<Game> createUser(@RequestBody Game game) {
       try {
        Game _game = gameRepository.save(new Game(game.getId(), game.getNameGame()));
           return new ResponseEntity<>(_game, HttpStatus.CREATED);
       } catch (Exception e) {
           return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    @PutMapping("/game/{id}")
    public ResponseEntity<Game> updateGame (@PathVariable("id") long id, @RequestBody Game game) {
        Optional<Game> gameData = gameRepository.findById(id);

        if (gameData.isPresent()) {
            Game _game = gameData.get();
            _game.setNameGame(game.getNameGame());
            return new ResponseEntity<>(gameRepository.save(_game), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/game/{id}")
    public ResponseEntity<HttpStatus> deleteGame (@PathVariable("id") long id){
        try {
            gameRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/game")
    public ResponseEntity<HttpStatus> deleteAllGame() {
        try{
            gameRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/game/byNameGame/{game}")
    public ResponseEntity<List<Game>> findByNameGame(@PathVariable("nameGame") String nameGame) {
        try {
            List<Game> games = gameRepository.findNameGame(nameGame);

            if (games.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(games, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
