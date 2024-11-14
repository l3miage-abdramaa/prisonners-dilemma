package fr.uga.m1miage.pc.joueur.strategies;

import fr.uga.m1miage.pc.partie.enums.CoupEnum;
import fr.uga.m1miage.pc.partie.models.PartieEntity;
import fr.uga.m1miage.pc.partie.models.PartieJoueurEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
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
        } else {
            coupsCooperationRestants = Math.max(0, coupsCooperationRestants - 1);
        }

        if (trahisonsAdversaire >= 2) {
            trahisonsAdversaire = 0;
            return CoupEnum.TRAHIR;
        }

        if (coupsCooperationRestants > 0) {
            return CoupEnum.COOPERER;
        }

        return CoupEnum.COOPERER;
    }
}