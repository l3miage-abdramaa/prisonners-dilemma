package fr.uga.m1miage.pc.serv.strategies;



import fr.uga.m1miage.pc.restApi.enums.CoupEnum;
import fr.uga.m1miage.pc.serv.models.PartieEntity;
import fr.uga.m1miage.pc.serv.models.PartieJoueurEntity;

import java.security.SecureRandom;
import java.util.List;

public class SondeurNaifStrategie implements StrategieInterface{

    private CoupEnum dernierCoupAdverse = null;
    private final SecureRandom secureRandom;

    public SondeurNaifStrategie() {
        this.dernierCoupAdverse = null;
        this.secureRandom = new SecureRandom();
    }


    @Override
    public CoupEnum getCoup(List<PartieEntity> parties) {
        List<PartieJoueurEntity> partieJoueurEntities = parties.stream()
                .flatMap(partie -> partie.getPartiesJoueur().stream())
                .toList();
        if (!partieJoueurEntities.isEmpty()) {
            PartieJoueurEntity dernierPartieJoueur = partieJoueurEntities.get(partieJoueurEntities.size() - 1);
            CoupEnum coupAdverse = dernierPartieJoueur.getCoup();
            if (coupAdverse != null) {
                dernierCoupAdverse = coupAdverse;
            }
        }

        if (dernierCoupAdverse != null && dernierCoupAdverse.equals(CoupEnum.COOPERER) && secureRandom.nextDouble() < 0.2) {
            return CoupEnum.TRAHIR;
        }
        return dernierCoupAdverse != null ? dernierCoupAdverse : CoupEnum.COOPERER;
    }
}
