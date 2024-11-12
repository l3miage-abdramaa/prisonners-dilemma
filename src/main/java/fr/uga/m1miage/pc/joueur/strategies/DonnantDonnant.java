package fr.uga.m1miage.pc.joueur.strategies;

import fr.uga.m1miage.pc.partie.enums.CoupEnum;
import fr.uga.m1miage.pc.partie.enums.StatutPartieEnum;
import fr.uga.m1miage.pc.partie.models.PartieEntity;
import fr.uga.m1miage.pc.partie.models.PartieJoueurEntity;

import java.util.List;
import java.util.Optional;

public class DonnantDonnant implements StrategieInterface{


    @Override
    public CoupEnum getCoup(List<PartieEntity> parties) {
        PartieEntity partieEnCours = recupererPartieEncours(parties).orElse(null);


        PartieEntity precedentePartie = parties.stream()
                .filter(partie -> partie.getOrdre() == partieEnCours.getOrdre() - 1)
                .findFirst()
                .orElse(null);

        if (precedentePartie == null) {
            return CoupEnum.COOPERER;
        }

        List<PartieJoueurEntity> partieJoueurs = precedentePartie.getPartiesJoueur();

        PartieJoueurEntity partieJoueurAdverse = partieJoueurs.stream()
                .filter(partieJoueur -> partieJoueur.getJoueur().getAbandon() == null)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Aucun joueur adverse trouve"));

        return partieJoueurAdverse.getCoup();
    }

    public Optional<PartieEntity> recupererPartieEncours(List<PartieEntity> parties) {
        return parties.stream().filter(partie -> partie.getStatut().equals(StatutPartieEnum.EN_COURS)).findAny();
    }
}
