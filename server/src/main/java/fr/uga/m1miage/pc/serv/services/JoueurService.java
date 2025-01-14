package fr.uga.m1miage.pc.serv.services;


import fr.uga.m1miage.pc.restApi.enums.StrategieEnum;
import fr.uga.m1miage.pc.serv.models.JoueurEntity;
import fr.uga.m1miage.pc.serv.repositories.JoueurRepository;
import fr.uga.m1miage.pc.serv.sse.JeuSseManager;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class JoueurService {
    private final JoueurRepository joueurRepository;

    private final JeuSseManager jeuSseManager;

    public JoueurService(JeuSseManager jeuSseManager, JoueurRepository joueurRepository) {
        this.jeuSseManager = jeuSseManager;
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
