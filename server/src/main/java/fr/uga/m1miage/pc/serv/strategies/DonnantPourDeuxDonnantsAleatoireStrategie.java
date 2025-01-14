package fr.uga.m1miage.pc.serv.strategies;



import fr.uga.m1miage.pc.restApi.enums.CoupEnum;
import fr.uga.m1miage.pc.serv.models.PartieEntity;
import fr.uga.m1miage.pc.serv.models.PartieJoueurEntity;

import java.security.SecureRandom;
import java.util.List;

public class DonnantPourDeuxDonnantsAleatoireStrategie implements StrategieInterface{

    private CoupEnum dernierCoupAdverse ;
    private int compteurCoupIdentique;

    private final SecureRandom secureRandom ;

    public DonnantPourDeuxDonnantsAleatoireStrategie() {
        this.dernierCoupAdverse =  null;
        this.compteurCoupIdentique = 0;
        this.secureRandom = new SecureRandom();
    }

    @Override
    public CoupEnum getCoup(List<PartieEntity> parties) {

        for (PartieEntity partie : parties) {
            for (PartieJoueurEntity joueur : partie.getPartiesJoueur()) {
                CoupEnum coupAdverse = joueur.getCoup();
                if (coupAdverse != null) {
                    if (coupAdverse.equals(dernierCoupAdverse)) {
                        compteurCoupIdentique++;
                    } else {
                        compteurCoupIdentique = 1;
                    }
                    dernierCoupAdverse = coupAdverse;
                }
            }
        }

        if (secureRandom.nextDouble() < 0.2) {
            return secureRandom.nextBoolean() ? CoupEnum.COOPERER : CoupEnum.TRAHIR;
        }

        if (compteurCoupIdentique >= 2) {
            return dernierCoupAdverse;
        }
        return CoupEnum.COOPERER;
    }
}
