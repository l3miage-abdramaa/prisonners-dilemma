package fr.uga.m1miage.pc.joueur.strategies;


import fr.uga.m1miage.pc.partie.enums.CoupEnum;
import fr.uga.m1miage.pc.partie.models.PartieEntity;
import fr.uga.m1miage.pc.partie.models.PartieJoueurEntity;

import java.security.SecureRandom;
import java.util.List;

public class PavlovAleatoireStrategie implements StrategieInterface {

    private CoupEnum dernierCoup;
    private SecureRandom random = new SecureRandom();

    @Override
    public CoupEnum getCoup(List<PartieEntity> parties) {
        // Si c'est le premier coup, coop�rer par d�faut
        if (parties.isEmpty()) {
            dernierCoup = CoupEnum.COOPERER;
            return dernierCoup;
        }

        // R�cup�rer la derni�re partie et le dernier coup de l'adversaire
        PartieEntity dernierePartie = parties.get(parties.size() - 1);
        PartieJoueurEntity dernierPartieJoueur = dernierePartie.getPartiesJoueur().get(0); // Supposons que l'adversaire est le premier joueur

        // Si le score du dernier tour est 5 ou 3, d�cider de r�p�ter le dernier coup ou de faire un choix al�atoire
        if (dernierPartieJoueur.getScore() == 5 || dernierPartieJoueur.getScore() == 3) {
            // 70% de chance de r�p�ter le dernier coup, 30% de chance de faire un choix al�atoire
            if (random.nextDouble() < 0.7) {
                return dernierCoup != null ? dernierCoup : CoupEnum.COOPERER; // Coop�rer par d�faut si aucun coup pr�c�dent
            }
        }

        // D�terminer le coup � jouer en fonction du dernier coup de l'adversaire
        CoupEnum dernierCoupAdversaire = dernierPartieJoueur.getCoup();

        if (dernierCoupAdversaire == CoupEnum.COOPERER) {
            dernierCoup = CoupEnum.COOPERER; // Si l'adversaire a coop�r�, coop�rer
        } else {
            dernierCoup = CoupEnum.TRAHIR; // Si l'adversaire a trahi, trahir
        }

        // Si on ne r�p�te pas le dernier coup, faire un choix al�atoire
        if (random.nextDouble() < 0.3) {
            dernierCoup = random.nextBoolean() ? CoupEnum.COOPERER : CoupEnum.TRAHIR; // Choix al�atoire
        }

        return dernierCoup;
    }
}
