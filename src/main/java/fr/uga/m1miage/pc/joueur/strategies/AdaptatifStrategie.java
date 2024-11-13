package fr.uga.m1miage.pc.joueur.strategies;


import fr.uga.m1miage.pc.partie.enums.CoupEnum;
import fr.uga.m1miage.pc.partie.models.PartieEntity;

import java.util.ArrayList;
import java.util.List;

public class AdaptatifStrategie implements StrategieInterface {

    private List<CoupEnum> historiqueCoup; // Historique des coups joués
    private List<Integer> scores; // Scores associés à chaque coup
    private CoupEnum dernierCoup; // Dernier coup joué
    private int compteur; // Compteur pour suivre le nombre de coups

    public AdaptatifStrategie() {
        historiqueCoup = new ArrayList<>();
        scores = new ArrayList<>();
        compteur = 0;

        // Initialiser avec la séquence c, c, c, c, c, c, t, t, t, t, t
        for (int i = 0; i < 6; i++) {
            historiqueCoup.add(CoupEnum.COOPERER);
            scores.add(0); // Initialiser les scores à 0
        }
        for (int i = 0; i < 5; i++) {
            historiqueCoup.add(CoupEnum.TRAHIR);
            scores.add(0); // Initialiser les scores à 0
        }
    }

    @Override
    public CoupEnum getCoup(List<PartieEntity> parties) {
        // Si c'est le premier coup, jouer le coup de la séquence
        if (compteur < historiqueCoup.size()) {
            dernierCoup = historiqueCoup.get(compteur);
            compteur++;
            return dernierCoup;
        }

        // Calculer le meilleur coup basé sur les scores
        double meilleurScore = -1;
        CoupEnum meilleurCoup = CoupEnum.COOPERER; // Par défaut, coopérer

        for (int i = 0; i < historiqueCoup.size(); i++) {
            double scoreMoyen = (double) scores.get(i) / (compteur > 6 ? 6 : compteur); // Éviter la division par zéro
            if (scoreMoyen > meilleurScore) {
                meilleurScore = scoreMoyen;
                meilleurCoup = historiqueCoup.get(i);
            }
        }

        // Mettre à jour le score du dernier coup joué
        if (dernierCoup != null) {
            int index = historiqueCoup.indexOf(dernierCoup);
            if (index != -1) {
                scores.set(index, scores.get(index) + (dernierCoup == meilleurCoup ? 1 : 0)); // Ajouter 1 si le dernier coup était le meilleur
            }
        }

        dernierCoup = meilleurCoup;
        return dernierCoup;
    }
}