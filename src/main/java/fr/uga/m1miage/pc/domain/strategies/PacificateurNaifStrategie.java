package fr.uga.m1miage.pc.domain.strategies;

import fr.uga.m1miage.pc.domain.enums.CoupEnum;
import fr.uga.m1miage.pc.domain.model.PartieEntity;
import fr.uga.m1miage.pc.domain.model.PartieJoueurEntity;

import java.security.SecureRandom;
import java.util.List;

public class PacificateurNaifStrategie implements StrategieInterface {

    private CoupEnum dernierCoupAdversaire;
    private SecureRandom random = new SecureRandom();
    private double probabiliteCooperation = 0.5;

    @Override
    public CoupEnum getCoup(List<PartieEntity> parties) {
        if (!parties.isEmpty()) {
            PartieEntity dernierePartie = parties.get(parties.size() - 1);
            List<PartieJoueurEntity> joueurs = dernierePartie.getPartiesJoueur();
            if (!joueurs.isEmpty()) {
                dernierCoupAdversaire = joueurs.get(joueurs.size() - 1).getCoup();
            }
        }

        if (dernierCoupAdversaire != null && dernierCoupAdversaire == CoupEnum.COOPERER) {
            return CoupEnum.COOPERER;
        }

        if (random.nextDouble() < probabiliteCooperation) {
            return CoupEnum.COOPERER;
        } else {
            return CoupEnum.TRAHIR;
        }
    }
}