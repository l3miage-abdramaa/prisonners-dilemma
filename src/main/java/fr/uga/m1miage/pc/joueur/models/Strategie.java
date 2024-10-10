package fr.uga.m1miage.pc.Joueur.models;

import fr.uga.m1miage.pc.Partie.enums.CoupEnum;
import fr.uga.m1miage.pc.Partie.enums.StatutPartieEnum;
import fr.uga.m1miage.pc.Partie.models.PartieEntity;
import fr.uga.m1miage.pc.partie.models.PartieJoueurEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Component
public class Strategie {

    private String strategie;

    public Strategie(String strategie) {
        this.strategie = strategie;
    }

    private CoupEnum toujoursTrahir() {
        return CoupEnum.TRAHIR;
    }


    private CoupEnum toujoursCooperer() {
        return CoupEnum.COOPERER;
    }

    private CoupEnum aleatoire() {
        CoupEnum[] coups = {CoupEnum.COOPERER, CoupEnum.TRAHIR};
        int randomNumber = new Random().nextInt(2);
        return coups[randomNumber];
    }

    private CoupEnum donnantDonnant(List<PartieEntity> parties) {
        Optional<PartieEntity> partieEnCours = parties.stream().filter(partie -> partie.getStatut().equals(StatutPartieEnum.EN_COURS)).findAny();
        Optional<PartieEntity> precedentePartie = partieEnCours.stream().filter(partie -> {
            return partie.getOrdre() == (partieEnCours.get().getOrdre()-1);
        }).findAny();
        List<PartieJoueurEntity> partieJoueurEntities = precedentePartie.get().getPartiesJoueur();
        PartieJoueurEntity partieJoueurAdverse = partieJoueurEntities.stream().filter(partieJoueur -> {
            return partieJoueur.getJoueur();
        });
        return null;
    }

    public CoupEnum getCoup() {
       return null;
    }
}
