package fr.uga.m1miage.pc.serv.config;


import fr.uga.m1miage.pc.serv.sse.JeuSseManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SseConfig {

    @Bean
    public JeuSseManager jeuSseManager() {
        return new JeuSseManager();
    }

}
