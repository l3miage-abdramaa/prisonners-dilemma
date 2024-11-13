package fr.uga.m1miage.pc.joueur.services;


import fr.uga.m1miage.pc.jeu.sse.JeuSseManager;
import fr.uga.m1miage.pc.joueur.enums.StrategieEnum;
import fr.uga.m1miage.pc.joueur.models.JoueurEntity;
import fr.uga.m1miage.pc.joueur.repository.JoueurRepository;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class JoueurService {
    private final JoueurRepository joueurRepository;

    private JeuSseManager jeuSseManager = JeuSseManager.getInstance();

    public JoueurService(JoueurRepository joueurRepository) {
        this.joueurRepository = joueurRepository;
    }

    public JoueurEntity abandonnerJeu(UUID idJoueur, StrategieEnum strategie) {
        Optional<JoueurEntity> joueur = joueurRepository.findById(idJoueur);
        if(joueur.isPresent()) {
            JoueurEntity joueur1 = joueur.get();
            joueur1.setStrategie(strategie);
            joueur1.setAbandon(true);
            
            jeuSseManager.notifier(joueur1.getJeu().getId());
            return joueurRepository.save(joueur1);
        }
        return null;

    }
}
