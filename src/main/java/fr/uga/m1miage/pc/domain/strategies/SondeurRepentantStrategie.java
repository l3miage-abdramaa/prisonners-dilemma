package fr.uga.m1miage.pc.domain.strategies;

import fr.uga.m1miage.pc.domain.enums.CoupEnum;
import fr.uga.m1miage.pc.domain.model.PartieEntity;
import fr.uga.m1miage.pc.domain.model.PartieJoueurEntity;

import java.security.SecureRandom;
import java.util.List;


public class SondeurRepentantStrategie implements StrategieInterface {

    private CoupEnum dernierCoupAdversaire;
    private SecureRandom random = new SecureRandom();
    private double probabiliteTrahison = 0.3;

    @Override
    public CoupEnum getCoup(List<PartieEntity> parties) {
        if (!parties.isEmpty()) {
            PartieEntity dernierePartie = parties.get(parties.size() - 1);
            List<PartieJoueurEntity> joueurs = dernierePartie.getPartiesJoueur();
            if (!joueurs.isEmpty()) {
                dernierCoupAdversaire = joueurs.get(joueurs.size() - 1).getCoup();
            }
        }

        if (dernierCoupAdversaire != null && dernierCoupAdversaire == CoupEnum.TRAHIR) {
            return CoupEnum.COOPERER;
        }

        if (random.nextDouble() < probabiliteTrahison) {
            return CoupEnum.TRAHIR;
        } else {
            return dernierCoupAdversaire != null ? dernierCoupAdversaire : CoupEnum.COOPERER;
        }
    }
}