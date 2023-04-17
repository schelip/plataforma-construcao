package com.equipe05.plataformaconstrucao.services;

import com.equipe05.plataformaconstrucao.model.User;
import com.equipe05.plataformaconstrucao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class UserService {
    @Autowired UserRepository UserRepository;

    public void setProfilePic(Long id, MultipartFile file) {
        User User = UserRepository.findById(id).orElseThrow();
        try {
            User.setProfilePic(file.getBytes());
            UserRepository.save(User);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public byte[] getProfilePic(Long id) {
        User User = UserRepository.findById(id).orElseThrow();
        return User.getProfilePic();
    }

    public void setBanner(Long id, MultipartFile file) {
        User User = UserRepository.findById(id).orElseThrow();
        try {
            User.setBanner(file.getBytes());
            UserRepository.save(User);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public byte[] getBanner(Long id) {
        User User = UserRepository.findById(id).orElseThrow();
        return User.getBanner();
    }
}
