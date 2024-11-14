package fr.uga.m1miage.pc.joueur.strategies;


import fr.uga.m1miage.pc.partie.enums.CoupEnum;
import fr.uga.m1miage.pc.partie.models.PartieEntity;

import java.util.ArrayList;
import java.util.List;

public class AdaptatifStrategie implements StrategieInterface {

    private List<CoupEnum> historiqueCoup;
    private List<Integer> scores;
    private CoupEnum dernierCoup;
    private int compteur;

    public AdaptatifStrategie() {
        historiqueCoup = new ArrayList<>();
        scores = new ArrayList<>();
        compteur = 0;

        for (int i = 0; i < 6; i++) {
            historiqueCoup.add(CoupEnum.COOPERER);
            scores.add(0);
        }
        for (int i = 0; i < 5; i++) {
            historiqueCoup.add(CoupEnum.TRAHIR);
            scores.add(0);
        }
    }

    @Override
    public CoupEnum getCoup(List<PartieEntity> parties) {
        if (compteur < historiqueCoup.size()) {
            dernierCoup = historiqueCoup.get(compteur);
            compteur++;
            return dernierCoup;
        }

        double meilleurScore = -1;
        CoupEnum meilleurCoup = CoupEnum.COOPERER;

        for (int i = 0; i < historiqueCoup.size(); i++) {
            double scoreMoyen = (double) scores.get(i) / (Math.min(compteur, 6));
            if (scoreMoyen > meilleurScore) {
                meilleurScore = scoreMoyen;
                meilleurCoup = historiqueCoup.get(i);
            }
        }

        if (dernierCoup != null) {
            int index = historiqueCoup.indexOf(dernierCoup);
            if (index != -1) {
                scores.set(index, scores.get(index) + (dernierCoup == meilleurCoup ? 1 : 0));
            }
        }

        dernierCoup = meilleurCoup;
        return dernierCoup;
    }
}