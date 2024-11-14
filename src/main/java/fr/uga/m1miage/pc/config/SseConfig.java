package fr.uga.m1miage.pc.config;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fr.uga.m1miage.pc.jeu.sse.JeuSseManager;

@Configuration
public class SseConfig {

    @Bean
    public JeuSseManager jeuSseManager() {
        return new JeuSseManager(new ConcurrentHashMap<>());
    }

}
