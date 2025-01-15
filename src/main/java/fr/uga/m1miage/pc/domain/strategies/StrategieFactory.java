package fr.uga.m1miage.pc.domain.strategies;

import fr.uga.m1miage.pc.domain.enums.StrategieEnum;
import fr.uga.miage.pc.g2_7.Aleatoire;
import fr.uga.miage.pc.g2_7.ToujoursTrahir;

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
            case DONNANT_DONNANT -> new DonnantDonnantStrategie();
            case RANCUNIER -> new RancunierStrategie();
            case DONNANT_DONNANT_ALEATOIRE -> new DonnantDonnantAleatoireStrategie();
            case DONNANT_POUR_DEUX_DONNANTS_ALEATOIRE -> new DonnantPourDeuxDonnantsAleatoireStrategie();
            case SONDEUR_NAIF -> new SondeurNaifStrategie();
            case SONDEUR_REPENTANT -> new SondeurRepentantStrategie();
            case PACIFICATEUR_NAIF -> new PacificateurNaifStrategie();
            case VRAI_PACIFICATEUR -> new VraiPacificateurStrategie();
            case PAVLOV -> new PavlovStrategie();
            case PAVLOV_ALEATOIRE -> new PavlovAleatoireStrategie();
            case ADAPTATIF -> new AdaptatifStrategie();
            case GRADUEL -> new GraduelStrategie();
            case DONNANT_DONNANT_SOUPCONNEUX -> new DonnantDonnantSoupconneuxStrategie();
            case RANCUNIEUX_DOUX -> new RancunierDouxStrategie();
            case DONNANT_POUR_DEUX_DONNANTS -> new DonnantPourDeuxDonnantsStrategie();
            case ALEATOIRE_G02_7 -> new StrategieBinomeAdapter(new Aleatoire());
            case TOUJOURS_TRAHIR_G02_7 -> new StrategieBinomeAdapter(new ToujoursTrahir());
        };
    }
}
