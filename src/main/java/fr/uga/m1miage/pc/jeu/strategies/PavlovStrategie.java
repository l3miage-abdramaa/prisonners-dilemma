package fr.uga.m1miage.pc.jeu.strategies;

import fr.uga.m1miage.pc.jeu.enums.CoupEnum;
import fr.uga.m1miage.pc.jeu.models.PartieEntity;
import fr.uga.m1miage.pc.jeu.models.PartieJoueurEntity;

import java.util.List;

public class PavlovStrategie implements StrategieInterface {

    private CoupEnum dernierCoup;

    @Override
    public CoupEnum getCoup(List<PartieEntity> parties) {
        if (parties.isEmpty()) {
            dernierCoup = CoupEnum.COOPERER;
            return dernierCoup;
        }

        PartieEntity dernierePartie = parties.get(parties.size() - 1);
        PartieJoueurEntity dernierPartieJoueur = dernierePartie.getPartiesJoueur().get(0);

        if (dernierPartieJoueur.getScore() == 5 || dernierPartieJoueur.getScore() == 3) {
            return dernierCoup != null ? dernierCoup : CoupEnum.COOPERER;
        }

        CoupEnum dernierCoupAdversaire = dernierPartieJoueur.getCoup();

        if (dernierCoupAdversaire == CoupEnum.COOPERER) {
            dernierCoup = CoupEnum.COOPERER;
        } else {
            dernierCoup = CoupEnum.TRAHIR;
        }

        return dernierCoup;
    }
}