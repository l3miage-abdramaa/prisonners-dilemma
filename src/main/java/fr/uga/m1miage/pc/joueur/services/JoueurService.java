package fr.uga.m1miage.pc.joueur.services;


import fr.uga.m1miage.pc.jeu.sse.JeuSseManager;
import fr.uga.m1miage.pc.joueur.enums.StrategieEnum;
import fr.uga.m1miage.pc.joueur.models.JoueurEntity;
import fr.uga.m1miage.pc.joueur.repository.JoueurRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JoueurService {
    @Autowired
    private JoueurRepository joueurRepository;

    @Autowired
    private JeuSseManager jeuSseManager;


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
