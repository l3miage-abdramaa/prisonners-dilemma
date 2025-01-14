package fr.uga.m1miage.pc.serv.strategies;



import fr.uga.m1miage.pc.restApi.enums.CoupEnum;
import fr.uga.m1miage.pc.serv.models.PartieEntity;
import fr.uga.m1miage.pc.serv.models.PartieJoueurEntity;

import java.util.List;

public class DonnantPourDeuxDonnantsStrategie implements StrategieInterface {

    private CoupEnum dernierCoupAdverse;
    private int compteurCoupIdentique;



    @Override
    public CoupEnum getCoup(List<PartieEntity> parties) {

        List<PartieJoueurEntity> partieJoueurEntities = parties.stream()
                .flatMap(partie -> partie.getPartiesJoueur().stream())
                .toList();

        if (!partieJoueurEntities.isEmpty()) {
            PartieJoueurEntity dernierPartieJoueur = partieJoueurEntities.get(partieJoueurEntities.size() - 1);
            CoupEnum coupAdverse = dernierPartieJoueur.getCoup();

            if (coupAdverse != null) {
                if (coupAdverse.equals(dernierCoupAdverse)) {
                    compteurCoupIdentique++;
                } else {
                    compteurCoupIdentique = 1;
                }
                dernierCoupAdverse = coupAdverse;
            }
        }

        if (compteurCoupIdentique >= 2) {
            return dernierCoupAdverse;
        }

        return CoupEnum.COOPERER;
    }
}