package fr.uga.m1miage.pc.joueur.strategies;

import fr.uga.m1miage.pc.partie.enums.CoupEnum;
import fr.uga.m1miage.pc.partie.models.PartieEntity;
import fr.uga.m1miage.pc.partie.models.PartieJoueurEntity;

import java.util.List;

public class GraduelStrategie implements StrategieInterface {
    private int trahisonsAdversaire = 0;
    private int coupsCooperationRestants = 0;

    @Override
    public CoupEnum getCoup(List<PartieEntity> parties) {
        PartieJoueurEntity adversaire = parties.stream()
                .flatMap(partie -> partie.getPartiesJoueur().stream())
                .filter(joueur -> !joueur.getJoueur().equals(this))
                .findFirst()
                .orElse(null);

        CoupEnum dernierCoupAdversaire = (adversaire != null) ? adversaire.getCoup() : null;

        if (dernierCoupAdversaire == CoupEnum.TRAHIR) {
            trahisonsAdversaire++;
            coupsCooperationRestants = 2;
        }

        if (coupsCooperationRestants > 0) {
            coupsCooperationRestants--;
            return CoupEnum.COOPERER;
        }

        if (trahisonsAdversaire >= 2) {
            trahisonsAdversaire = 0;
            return CoupEnum.TRAHIR;
        }

        return CoupEnum.COOPERER;
    }
}