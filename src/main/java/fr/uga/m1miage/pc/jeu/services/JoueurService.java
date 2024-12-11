package fr.uga.m1miage.pc.jeu.services;


import fr.uga.m1miage.pc.jeu.sse.JeuSseManager;
import fr.uga.m1miage.pc.jeu.enums.StrategieEnum;
import fr.uga.m1miage.pc.joueur.models.JoueurEntity;
import fr.uga.m1miage.pc.jeu.repository.JoueurRepository;
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
