package fr.uga.m1miage.pc.infrastructure.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fr.uga.m1miage.pc.infrastructure.sse.JeuSseManager;

@Configuration
public class SseConfig {

    @Bean
    public JeuSseManager jeuSseManager() {
        return new JeuSseManager();
    }

}
