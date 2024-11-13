package fr.uga.m1miage.pc.joueur.strategies;


import fr.uga.m1miage.pc.partie.enums.CoupEnum;
import fr.uga.m1miage.pc.partie.models.PartieEntity;

import java.security.SecureRandom;
import java.util.List;


public class VraiPacificateurStrategie implements StrategieInterface {

    private CoupEnum dernierCoupAdversaire;
    private int nombreDeTrahisonsConsecutives;
    private SecureRandom random;

    public VraiPacificateurStrategie() {
        this.dernierCoupAdversaire = null;
        this.nombreDeTrahisonsConsecutives = 0;
        this.random = new SecureRandom();
    }

    @Override
    public CoupEnum getCoup(List<PartieEntity> parties) {
        if (!parties.isEmpty()) {
            PartieEntity dernierePartie = parties.get(parties.size() - 1);
            dernierCoupAdversaire = dernierePartie.getPartiesJoueur().get(0).getCoup(); // Supposons que l'adversaire est le premier joueur
        }

        if (dernierCoupAdversaire == null) {
            return CoupEnum.COOPERER;
        } else if (dernierCoupAdversaire == CoupEnum.TRAHIR) {
            nombreDeTrahisonsConsecutives++;
            if (nombreDeTrahisonsConsecutives >= 2) {
                return CoupEnum.TRAHIR;
            } else {
                // Essayer de faire la paix avec 50% de chance de coopérer
                return random.nextBoolean() ? CoupEnum.COOPERER : CoupEnum.TRAHIR;
            }
        } else {
            // Si l'adversaire a coopéré, continuer à coopérer
            nombreDeTrahisonsConsecutives = 0;
            return CoupEnum.COOPERER;
        }
    }
}