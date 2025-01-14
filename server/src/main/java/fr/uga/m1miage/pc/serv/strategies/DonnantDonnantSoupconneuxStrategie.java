package fr.uga.m1miage.pc.serv.strategies;


import fr.uga.m1miage.pc.restApi.enums.CoupEnum;
import fr.uga.m1miage.pc.serv.models.PartieEntity;
import fr.uga.m1miage.pc.serv.models.PartieJoueurEntity;

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