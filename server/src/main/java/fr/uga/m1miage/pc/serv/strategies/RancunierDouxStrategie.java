package fr.uga.m1miage.pc.serv.strategies;


import fr.uga.m1miage.pc.restApi.enums.CoupEnum;
import fr.uga.m1miage.pc.serv.models.PartieEntity;
import fr.uga.m1miage.pc.serv.models.PartieJoueurEntity;

import java.util.List;

public class RancunierDouxStrategie implements StrategieInterface {

    @Override
    public CoupEnum getCoup(List<PartieEntity> parties) {

        PartieJoueurEntity adversaire = parties.stream()
                .flatMap(partie -> partie.getPartiesJoueur().stream())
                .filter(joueur -> !joueur.getJoueur().equals(this))
                .findFirst()
                .orElse(null);

        if (adversaire == null) {
            return CoupEnum.COOPERER;
        }

        CoupEnum dernierCoupAdversaire = adversaire.getCoup();

        if (dernierCoupAdversaire == CoupEnum.TRAHIR) {
            return CoupEnum.TRAHIR;
        }

        return CoupEnum.COOPERER;
    }
}