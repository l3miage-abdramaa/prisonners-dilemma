package fr.uga.m1miage.pc.jeu.services;


import fr.uga.m1miage.pc.jeu.enums.StatutJeuEnum;
import fr.uga.m1miage.pc.jeu.models.JeuEntity;
import fr.uga.m1miage.pc.jeu.repository.JeuRepository;
import fr.uga.m1miage.pc.jeu.sse.JeuSseManager;
import fr.uga.m1miage.pc.jeu.models.JoueurEntity;
import fr.uga.m1miage.pc.jeu.repository.JoueurRepository;
import fr.uga.m1miage.pc.jeu.strategies.Strategie;
import fr.uga.m1miage.pc.jeu.enums.CoupEnum;
import fr.uga.m1miage.pc.jeu.enums.StatutPartieEnum;
import fr.uga.m1miage.pc.jeu.models.PartieEntity;
import fr.uga.m1miage.pc.jeu.models.PartieJoueurEntity;
import fr.uga.m1miage.pc.jeu.repository.PartieJoueurRepository;
import fr.uga.m1miage.pc.jeu.repository.PartieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PartieService {

    private final JeuRepository jeuRepository;

    private  final JoueurRepository joueurRepository;

    private  final PartieRepository partieRepository;

    private final PartieJoueurRepository partieJoueurRepository;

    private final JeuSseManager jeuSseManager;

    public PartieService(JeuRepository jeuRepository, JoueurRepository joueurRepository, PartieRepository partieRepository, PartieJoueurRepository partieJoueurRepository, JeuSseManager jeuSseManager) {
        this.jeuRepository = jeuRepository;
        this.joueurRepository = joueurRepository;
        this.partieRepository = partieRepository;
        this.partieJoueurRepository = partieJoueurRepository;
        this.jeuSseManager = jeuSseManager;
    }

    public PartieJoueurEntity jouerCoup(UUID idJoueur, Long idJeu, CoupEnum coup) {
        JoueurEntity joueur = joueurRepository.findById(idJoueur).orElseThrow();

        PartieEntity partieEnCours = partieRepository.findByJeuIdAndStatut(idJeu,StatutPartieEnum.EN_COURS);

        PartieJoueurEntity partieJoueur = PartieJoueurEntity
                .builder()
                .partie(partieEnCours)
                .coup(coup)
                .joueur(joueur)
                .build();
        partieJoueurRepository.save(partieJoueur);


        if (regarderSiJoueurAdverseAAbandonne(idJeu)) {
            jouerServeurCoup(idJeu);
        }

        if(partieEnCours.getPartiesJoueur().size() == 2) {
            terminerPartie(partieEnCours);
        }

        if (joueur.getJeu().getNombreParties() == joueur.getJeu().getParties().size()) {
            terminerJeu(joueur.getJeu());
        }

        jeuSseManager.notifier(idJeu);
        return partieJoueur;
    }

    public boolean regarderSiJoueurAdverseAAbandonne(Long idJeu) {
        JeuEntity jeu = jeuRepository.findById(idJeu).orElseThrow(() -> new IllegalArgumentException("Jeu non trouvé"));

        for (JoueurEntity joueur : jeu.getJoueurs()) {
            if (joueur.getAbandon() != null) {
                return true;
            }
        }

        return false;
    }

    public void jouerServeurCoup(Long idJeu) {
        JeuEntity jeu = jeuRepository.findById(idJeu).orElseThrow();
        JoueurEntity joueurAbandonne = jeu.getJoueurs().stream().filter(joueur -> joueur.getAbandon() != null).findFirst().orElse(null);
        if (joueurAbandonne != null) {
            Strategie strategie = new Strategie();
            PartieEntity partieEnCours = partieRepository.findByJeuIdAndStatut(idJeu,StatutPartieEnum.EN_COURS);
            PartieJoueurEntity partieJoueur = PartieJoueurEntity
                    .builder()
                    .partie(partieEnCours)
                    .coup(strategie.getCoup(jeu.getParties(), joueurAbandonne.getStrategie()))
                    .joueur(joueurAbandonne)
                    .build();
            partieJoueurRepository.save(partieJoueur);

        }
    }


    public void terminerPartie(PartieEntity partieEnCours) {
        if (partieEnCours.getPartiesJoueur() != null && !partieEnCours.getPartiesJoueur().isEmpty()) {
            calculerScore(partieEnCours.getPartiesJoueur());
        } else {
            throw new IllegalStateException("La liste des joueurs est vide ou nulle.");
        }
        creerNouvellePartie(partieEnCours.getJeu(), partieEnCours.getOrdre() + 1);
        partieEnCours.setStatut(StatutPartieEnum.TERMINE);
        partieRepository.save(partieEnCours);
    }

    public void creerNouvellePartie(JeuEntity jeu, int ordre)  {
        if (jeu.getNombreParties() > jeu.getParties().size()) {
            PartieEntity partie = PartieEntity
                    .builder()
                    .jeu(jeu)
                    .ordre(ordre)
                    .statut(StatutPartieEnum.EN_COURS)
                    .build();
            partieRepository.save(partie);
        }
    }

    public void terminerJeu(JeuEntity jeu) {
        List<PartieEntity> partieEntities = jeu.getParties().stream().filter(partie -> partie.getStatut().equals(StatutPartieEnum.EN_COURS)).toList();
        if (partieEntities.isEmpty()) {
            jeu.setStatut(StatutJeuEnum.TERMINE);
            jeuRepository.save(jeu);
        }
    }

    public void calculerScore(List<PartieJoueurEntity> partiesJoueurs) {
        PartieJoueurEntity partieJoueur1 = partiesJoueurs.get(0);
        PartieJoueurEntity partieJoueur2 = partiesJoueurs.get(1);

        if (partieJoueur1.getCoup().equals(CoupEnum.COOPERER) && partieJoueur2.getCoup().equals(CoupEnum.COOPERER)) {
            partieJoueur1.setScore(3);
            partieJoueur2.setScore(3);
        }
        if (partieJoueur1.getCoup().equals(CoupEnum.COOPERER) && partieJoueur2.getCoup().equals(CoupEnum.TRAHIR)) {
            partieJoueur1.setScore(0);
            partieJoueur2.setScore(5);
        }
        if (partieJoueur1.getCoup().equals(CoupEnum.TRAHIR) && partieJoueur2.getCoup().equals(CoupEnum.COOPERER)) {
            partieJoueur1.setScore(5);
            partieJoueur2.setScore(0);
        }
        if (partieJoueur1.getCoup().equals(CoupEnum.TRAHIR) && partieJoueur2.getCoup().equals(CoupEnum.TRAHIR)) {
            partieJoueur1.setScore(1);
            partieJoueur2.setScore(1);
        }
        partieJoueurRepository.saveAll(List.of(partieJoueur1,partieJoueur2));
    }

    public PartieEntity recupererDetailsPartie(UUID idPartie) {
        return partieRepository.findById(idPartie).orElseThrow();
    }
}