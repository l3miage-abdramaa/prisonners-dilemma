package fr.uga.m1miage.pc.joueur.strategies;


import fr.uga.m1miage.pc.partie.enums.CoupEnum;
import fr.uga.m1miage.pc.partie.models.PartieEntity;
import fr.uga.m1miage.pc.partie.models.PartieJoueurEntity;

import java.util.List;

public class DonnantDonnantSoupconneuxStrategie implements StrategieInterface {
    private boolean aTrahi = false;

    @Override
    public CoupEnum getCoup(List<PartieEntity> parties) {
        if (!aTrahi) {
            aTrahi = true;
            return CoupEnum.TRAHIR;
        }
        PartieJoueurEntity adversaire = parties.stream()
                .flatMap(partie -> partie.getPartiesJoueur().stream())
                .filter(joueur -> !joueur.getJoueur().equals(this))
                .findFirst()
                .orElse(null);
        if (adversaire != null) {
            CoupEnum dernierCoupAdversaire = adversaire.getCoup();
            return dernierCoupAdversaire != null ? dernierCoupAdversaire : CoupEnum.COOPERER;
        }
        return CoupEnum.COOPERER;
    }
}