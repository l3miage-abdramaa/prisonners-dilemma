package fr.uga.m1miage.pc.domain.strategies;

import fr.uga.m1miage.pc.domain.enums.CoupEnum;
import fr.uga.m1miage.pc.domain.model.PartieEntity;
import fr.uga.m1miage.pc.domain.model.PartieJoueurEntity;

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