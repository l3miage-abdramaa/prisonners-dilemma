package fr.uga.m1miage.pc.joueur.strategies;

import fr.uga.m1miage.pc.joueur.enums.StrategieEnum;
public class StrategieFactory {

    private StrategieFactory(){}

    public static StrategieInterface getStrategie(StrategieEnum strategieEnum) {

        if (strategieEnum == null) {
            throw new IllegalArgumentException("Strategie inconnue : null");
        }

        return switch (strategieEnum) {
            case TOUJOURS_TRAHIR -> new ToujoursTrahirStrategie();
            case TOUJOURS_COOPERER -> new ToujoursCoopererStrategie();
            case ALEATOIRE -> new AleatoireStrategie();
            case DONNANT_DONNANT -> new DonnantDonnant();
            case RANCUNIER -> new RancunierStrategie();
            case DONNANT_DONNANT_ALEATOIRE -> new DonnantDonnantAleatoireStrategie();
        };
    }
}
