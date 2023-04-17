package com.equipe05.plataformaconstrucao.controller;

import com.equipe05.plataformaconstrucao.model.skills.SkillCategory;
import com.equipe05.plataformaconstrucao.repository.SkillCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class SkillCategoryController {
    @Autowired SkillCategoryRepository categoryRepository;

    @PostMapping("/skill/category")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<SkillCategory> create(@RequestBody SkillCategory skillCategory) {
        try {
            SkillCategory _category = categoryRepository.save(skillCategory);
            return new ResponseEntity<>(_category, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/skill/category/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<SkillCategory> update(@PathVariable("id") long id, @RequestBody SkillCategory category) {
        Optional<SkillCategory> categoryData = categoryRepository.findById(id);

        if (categoryData.isPresent()) {
            SkillCategory _category = categoryData.get();
            _category.setName(category.getName());
            _category.setGame(category.getGame());
            return new ResponseEntity<>(categoryRepository.save(_category), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/skill/category/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id){
        try {
            categoryRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
