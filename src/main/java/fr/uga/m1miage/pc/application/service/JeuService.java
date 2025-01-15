package fr.uga.m1miage.pc.application.service;


import fr.uga.m1miage.pc.domain.enums.StatutJeuEnum;
import fr.uga.m1miage.pc.domain.exceptions.JeuCreationException;
import fr.uga.m1miage.pc.domain.model.JeuEntity;
import fr.uga.m1miage.pc.domain.port.JeuRepositoryPort;
import fr.uga.m1miage.pc.domain.port.JoueurRepositoryPort;
import fr.uga.m1miage.pc.domain.port.PartieRepositoryPort;
import fr.uga.m1miage.pc.infrastructure.adapter.repository.JeuRepository;
import fr.uga.m1miage.pc.infrastructure.sse.JeuSseManager;
import fr.uga.m1miage.pc.domain.model.JoueurEntity;
import fr.uga.m1miage.pc.infrastructure.adapter.repository.JoueurRepository;
import fr.uga.m1miage.pc.domain.enums.StatutPartieEnum;
import fr.uga.m1miage.pc.domain.model.PartieEntity;
import fr.uga.m1miage.pc.infrastructure.adapter.repository.PartieRepository;
import org.springframework.stereotype.Service;

@Service
public class JeuService {
    private final JoueurRepositoryPort joueurRepository;
    private final JeuRepositoryPort jeuRepository;
    private final PartieRepositoryPort partieRepository;
    private final JeuSseManager jeuSseManager;


    public JeuService(JoueurRepositoryPort joueurRepository, JeuRepositoryPort jeuRepository, PartieRepositoryPort partieRepository, JeuSseManager jeuSseManager) {
        this.joueurRepository = joueurRepository;
        this.jeuRepository = jeuRepository;
        this.partieRepository = partieRepository;
        this.jeuSseManager = jeuSseManager;
    }

    public JeuEntity creerJeu(String nomJoueur, int nombreParties) {
        try {
            
            JeuEntity jeu = JeuEntity
                    .builder()
                    .nombreParties(nombreParties)
                    .statut(StatutJeuEnum.EN_ATTENTE)
                    .build();
            JeuEntity jeuEnregistre = jeuRepository.save(jeu);
    
            JoueurEntity joueur = JoueurEntity
                    .builder()
                    .nomJoueur(nomJoueur)
                    .jeu(jeuEnregistre)
                    .build();
            joueurRepository.save(joueur);

            jeuEnregistre.setJoueurCree(joueur);
    
            return jeuEnregistre;
        } catch (Exception e) {
            throw new JeuCreationException(e.getMessage(),e.getCause());
        }
    }




    public JeuEntity joindreJeu(String pseudo, Long idJeu) {
        JeuEntity jeu = jeuRepository.findById(idJeu).orElseThrow();
        if (jeu.getStatut().equals(StatutJeuEnum.EN_COURS)) {
            throw new IllegalArgumentException("Le nombre de joueurs est atteint");
        }
        JoueurEntity secondJoueur = JoueurEntity
                .builder()
                .jeu(jeu)
                .nomJoueur(pseudo)
                .build();
        joueurRepository.save(secondJoueur);
        PartieEntity partie = PartieEntity
                .builder()
                .jeu(jeu)
                .ordre(1)
                .statut(StatutPartieEnum.EN_COURS)
                .build();
        partieRepository.save(partie);
        jeu.setStatut(StatutJeuEnum.EN_COURS);

        JeuEntity jeuMisAJour = jeuRepository.save(jeu);
        jeuMisAJour.setJoueurCree(secondJoueur);
        jeuSseManager.notifier(idJeu);
        return jeuMisAJour;
    }


    public JeuEntity recupererJeu(Long idJeu) {
        return jeuRepository.findById(idJeu).orElseThrow();
    }
}
