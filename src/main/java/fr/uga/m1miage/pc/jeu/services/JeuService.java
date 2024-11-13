package fr.uga.m1miage.pc.jeu.services;


import fr.uga.m1miage.pc.jeu.enums.StatutJeuEnum;
import fr.uga.m1miage.pc.jeu.exceptions.JeuCreationException;
import fr.uga.m1miage.pc.jeu.models.JeuEntity;
import fr.uga.m1miage.pc.jeu.repository.JeuRepository;
import fr.uga.m1miage.pc.jeu.sse.JeuSseManager;
import fr.uga.m1miage.pc.joueur.models.JoueurEntity;
import fr.uga.m1miage.pc.joueur.repository.JoueurRepository;
import fr.uga.m1miage.pc.partie.enums.StatutPartieEnum;
import fr.uga.m1miage.pc.partie.models.PartieEntity;
import fr.uga.m1miage.pc.partie.repository.PartieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JeuService {
    @Autowired
    JoueurRepository joueurRepository ;
    @Autowired
    JeuRepository jeuRepository;
    @Autowired
    PartieRepository partieRepository;

    private JeuSseManager jeuSseManager = JeuSseManager.getInstance();

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
