package com.equipe05.plataformaconstrucao.Controller;

import com.equipe05.plataformaconstrucao.Repository.RankingRepository;
import com.equipe05.plataformaconstrucao.model.Ranking;

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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RankingController {
    @Autowired
    RankingRepository rankRepository;

    @GetMapping("/rank")
    public ResponseEntity<List<Ranking>> getAllRanking(@RequestParam(required = false) String name) {
        try {
            List<Ranking> rank = new ArrayList<rank>();

            if (name == null)
                rankRepository.findAll().forEach(rank::add);
            else
                rankRepository.findByName(name).forEach(rank::add);
            if (rank.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(rank, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/rank/{id}")
    public ResponseEntity<rank> getRankingById(@PathVariable("id") long id) {
        Optional<rank> rankData = rankRepository.findById(id);

        if (rankData.isPresent()) {
            return new ResponseEntity<>(rankData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/rank")
    public ResponseEntity<Ranking> createRanking(@RequestBody Ranking rank) {
        try {
            Ranking _rank = rankRepository.save(new Ranking(rank.getId(), rank.getName(), rank.getExperience(), rank.getDescription()));
            return new ResponseEntity<>(_rank, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/rank/{id}")
    public ResponseEntity<Ranking> updateRanking(@PathVariable("id") long id, @RequestBody Ranking rank) {
        Optional<Ranking> rankData = rankRepository.findById(id);

        if (rankData.isPresent()) {
            Ranking _rank = rankData.get();
            _rank.setName(rank.getName());
            _rank.setExperience(rank.getExperience());
            _rank.setDescription(rank.getDescription());
            return new ResponseEntity<>(rankRepository.save(_rank), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/rank/{id}")
    public ResponseEntity<HttpStatus> deleteRanking(@PathVariable("id") long id){
        try {
            rankRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/rank")
    public ResponseEntity<HttpStatus> deleteAllRanking() {
        try{
            rankRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/rank/byName/{Name}")
    public ResponseEntity<List<Ranking>> findByName(@PathVariable("name") String name) {
        try {
            List<Ranking> ranks = rankRepository.findByName(name);

            if (ranks.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(ranks, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
