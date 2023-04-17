package com.equipe05.plataformaconstrucao.controller;

import com.equipe05.plataformaconstrucao.model.Game;
import com.equipe05.plataformaconstrucao.model.skills.Skill;
import com.equipe05.plataformaconstrucao.model.skills.SkillCategory;
import com.equipe05.plataformaconstrucao.repository.GameRepository;
import com.equipe05.plataformaconstrucao.repository.SkillCategoryRepository;
import com.equipe05.plataformaconstrucao.repository.SkillRepository;
import com.equipe05.plataformaconstrucao.services.SkillService;
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
public class SkillController {
    @Autowired GameRepository gameRepository;
    @Autowired SkillRepository skillRepository;
    @Autowired SkillCategoryRepository categoryRepository;
    @Autowired SkillService skillService;

    @GetMapping("/game/{id}/skills")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<SkillCategory>> getByGameId(@PathVariable("id") long id) {
        Optional<Game> game = gameRepository.findById(id);

        if (game.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<SkillCategory> skillCategories = categoryRepository.findByGame(game.get());

        if (skillCategories.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(skillCategories, HttpStatus.OK);
    }

    @GetMapping("/skills/category/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<Skill>> getByCategory(@PathVariable("id") long id) {
        Optional<SkillCategory> category = categoryRepository.findById(id);

        if (category.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Skill> skills = skillRepository.findBySkillCategory(category.get());

        if (skills.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(skills, HttpStatus.OK);
    }

    @PostMapping("/skill")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Skill> create(@RequestBody Skill skill) {
        try {
            Skill _skill = skillRepository.save(skill);
            return new ResponseEntity<>(_skill, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/skill/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Skill> update(@PathVariable("id") long id, @RequestBody Skill skill) {
        Optional<Skill> skillData = skillRepository.findById(id);

        if (skillData.isPresent()) {
            Skill _skill = skillData.get();
            _skill.setName(skill.getName());
            _skill.setSkillCategory(skill.getSkillCategory());
            _skill.setTracked(skill.getTracked());
            _skill.setJsonPath(skill.getJsonPath());
            return new ResponseEntity<>(skillRepository.save(_skill), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/skill/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id){
        try {
            skillRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/skill")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<HttpStatus> deleteAll() {
        try{
            skillRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/skill/{id}/icon")
    public ResponseEntity<?> getIcon(@PathVariable("id") Long id) {
        try {
            byte[] bytes = skillService.getSkillIcon(id);
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

    @PostMapping(path = "/skill/{id}/icon", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<HttpStatus> setIcon(@PathVariable("id") Long id, @RequestParam MultipartFile file) {
        try {
            skillService.setSkillIcon(id, file);
            return ResponseEntity.created(new URI(MessageFormat.format("/api/game/{0}/icon", id))).build();
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
