package fr.uga.m1miage.pc.joueur.strategies;

import fr.uga.m1miage.pc.partie.enums.CoupEnum;
import fr.uga.m1miage.pc.partie.models.PartieEntity;
import fr.uga.m1miage.pc.partie.models.PartieJoueurEntity;

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

        // Vérifier le dernier coup de l'adversaire
        CoupEnum dernierCoupAdversaire = adversaire.getCoup();

        // Si l'adversaire a trahi, punir en trahissant
        if (dernierCoupAdversaire == CoupEnum.TRAHIR) {
            return CoupEnum.TRAHIR; // Trahir en réponse à la trahison
        }

        // Si l'adversaire a coopéré, coopérer
        return CoupEnum.COOPERER;
    }
}