package com.equipe05.plataformaconstrucao.services;

import com.equipe05.plataformaconstrucao.model.skills.Skill;
import com.equipe05.plataformaconstrucao.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class SkillService {
    @Autowired
    SkillRepository skillRepository;

    public void setSkillIcon(Long id, MultipartFile file) {
        Skill skill = skillRepository.findById(id).orElseThrow();
        try {
            skill.setIcon(file.getBytes());
            skillRepository.save(skill);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public byte[] getSkillIcon(Long id) {
        Skill skill = skillRepository.findById(id).orElseThrow();
        return skill.getIcon();
    }
}
