package com.equipe05.plataformaconstrucao.services;

import com.equipe05.plataformaconstrucao.model.Game;
import com.equipe05.plataformaconstrucao.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class GameService {
    @Autowired GameRepository gameRepository;

    public void setGameIcon(Long id, MultipartFile file) {
        System.out.println(id);
        Game game = gameRepository.findById(id).orElseThrow();
        try {
            game.setIcon(file.getBytes());
            gameRepository.save(game);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public byte[] getGameIcon(Long id) {
        Game game = gameRepository.findById(id).orElseThrow();
        return game.getIcon();
    }

    public void setGameBackgroundImage(Long id, MultipartFile file) {
        Game game = gameRepository.findById(id).orElseThrow();
        try {
            game.setBackgroundImage(file.getBytes());
            gameRepository.save(game);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public byte[] getGameBackgroundImage(Long id) {
        Game game = gameRepository.findById(id).orElseThrow();
        return game.getBackgroundImage();
    }
}
