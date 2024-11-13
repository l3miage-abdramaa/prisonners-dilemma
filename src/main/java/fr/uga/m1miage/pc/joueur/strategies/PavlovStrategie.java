package fr.uga.m1miage.pc.joueur.strategies;

import fr.uga.m1miage.pc.partie.enums.CoupEnum;
import fr.uga.m1miage.pc.partie.models.PartieEntity;
import fr.uga.m1miage.pc.partie.models.PartieJoueurEntity;

import java.util.List;

public class PavlovStrategie implements StrategieInterface {

    private CoupEnum dernierCoup;

    @Override
    public CoupEnum getCoup(List<PartieEntity> parties) {
        // Si c'est le premier coup, coopérer par défaut
        if (parties.isEmpty()) {
            dernierCoup = CoupEnum.COOPERER;
            return dernierCoup;
        }

        // Récupérer le dernier coup de l'adversaire
        PartieEntity dernierePartie = parties.get(parties.size() - 1);
        PartieJoueurEntity dernierPartieJoueur = dernierePartie.getPartiesJoueur().get(0); // Supposons que l'adversaire est le premier joueur

        // Si le score du dernier tour est 5 ou 3, répéter le dernier coup
        if (dernierPartieJoueur.getScore() == 5 || dernierPartieJoueur.getScore() == 3) {
            return dernierCoup != null ? dernierCoup : CoupEnum.COOPERER; // Coopérer par défaut si aucun coup précédent
        }

        // Déterminer le coup à jouer en fonction du dernier coup de l'adversaire
        CoupEnum dernierCoupAdversaire = dernierPartieJoueur.getCoup();

        if (dernierCoupAdversaire == CoupEnum.COOPERER) {
            dernierCoup = CoupEnum.COOPERER; // Si l'adversaire a coopéré, coopérer
        } else {
            dernierCoup = CoupEnum.TRAHIR; // Si l'adversaire a trahi, trahir
        }

        return dernierCoup;
    }
}