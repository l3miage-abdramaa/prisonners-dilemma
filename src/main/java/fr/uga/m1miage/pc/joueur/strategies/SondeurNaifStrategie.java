package fr.uga.m1miage.pc.joueur.strategies;

import fr.uga.m1miage.pc.partie.enums.CoupEnum;
import fr.uga.m1miage.pc.partie.models.PartieEntity;
import fr.uga.m1miage.pc.partie.models.PartieJoueurEntity;

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
        // Vérification des coups précédents
        List<PartieJoueurEntity> partieJoueurEntities = parties.stream()
                .flatMap(partie -> partie.getPartiesJoueur().stream())
                .toList();

        // Vérification du dernier coup adverse
        if (!partieJoueurEntities.isEmpty()) {
            PartieJoueurEntity dernierPartieJoueur = partieJoueurEntities.get(partieJoueurEntities.size() - 1);
            CoupEnum coupAdverse = dernierPartieJoueur.getCoup();
            // Mettre à jour le dernier coup adverse
            if (coupAdverse != null) {
                dernierCoupAdverse = coupAdverse;
            }
        }

        if (dernierCoupAdverse != null && dernierCoupAdverse.equals(CoupEnum.COOPERER) && secureRandom.nextDouble() < 0.2) {
            return CoupEnum.TRAHIR; // 20% de chance de trahir
        }
        return dernierCoupAdverse != null ? dernierCoupAdverse : CoupEnum.COOPERER; // Coopérer par défaut si c'est le premier coup
    }
}
