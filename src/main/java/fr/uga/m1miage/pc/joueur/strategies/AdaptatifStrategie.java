package fr.uga.m1miage.pc.joueur.strategies;


import fr.uga.m1miage.pc.partie.enums.CoupEnum;
import fr.uga.m1miage.pc.partie.models.PartieEntity;

import java.util.ArrayList;
import java.util.List;

public class AdaptatifStrategie implements StrategieInterface {

    private List<CoupEnum> historiqueCoup; // Historique des coups jou�s
    private List<Integer> scores; // Scores associ�s � chaque coup
    private CoupEnum dernierCoup; // Dernier coup jou�
    private int compteur; // Compteur pour suivre le nombre de coups

    public AdaptatifStrategie() {
        historiqueCoup = new ArrayList<>();
        scores = new ArrayList<>();
        compteur = 0;

        // Initialiser avec la s�quence c, c, c, c, c, c, t, t, t, t, t
        for (int i = 0; i < 6; i++) {
            historiqueCoup.add(CoupEnum.COOPERER);
            scores.add(0); // Initialiser les scores � 0
        }
        for (int i = 0; i < 5; i++) {
            historiqueCoup.add(CoupEnum.TRAHIR);
            scores.add(0); // Initialiser les scores � 0
        }
    }

    @Override
    public CoupEnum getCoup(List<PartieEntity> parties) {
        // Si c'est le premier coup, jouer le coup de la s�quence
        if (compteur < historiqueCoup.size()) {
            dernierCoup = historiqueCoup.get(compteur);
            compteur++;
            return dernierCoup;
        }

        // Calculer le meilleur coup bas� sur les scores
        double meilleurScore = -1;
        CoupEnum meilleurCoup = CoupEnum.COOPERER; // Par d�faut, coop�rer

        for (int i = 0; i < historiqueCoup.size(); i++) {
            double scoreMoyen = (double) scores.get(i) / (compteur > 6 ? 6 : compteur); // �viter la division par z�ro
            if (scoreMoyen > meilleurScore) {
                meilleurScore = scoreMoyen;
                meilleurCoup = historiqueCoup.get(i);
            }
        }

        // Mettre � jour le score du dernier coup jou�
        if (dernierCoup != null) {
            int index = historiqueCoup.indexOf(dernierCoup);
            if (index != -1) {
                scores.set(index, scores.get(index) + (dernierCoup == meilleurCoup ? 1 : 0)); // Ajouter 1 si le dernier coup �tait le meilleur
            }
        }

        dernierCoup = meilleurCoup;
        return dernierCoup;
    }
}