package fr.uga.m1miage.pc.joueur.strategies;

import static org.junit.jupiter.api.Assertions.*;
import fr.uga.m1miage.pc.partie.enums.CoupEnum;
import fr.uga.m1miage.pc.partie.models.PartieEntity;
import fr.uga.m1miage.pc.partie.models.PartieJoueurEntity;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class DonnantDonnantAleatoireStrategieTest {

    @Test
    void testGetCoupAvecCoupAdversaire() {
        PartieJoueurEntity adversaire = new PartieJoueurEntity();
        adversaire.setCoup(CoupEnum.TRAHIR);

        List<PartieJoueurEntity> partieJoueurEntities = new ArrayList<>();
        partieJoueurEntities.add(adversaire);

        PartieEntity partieEntity = new PartieEntity();
        partieEntity.setPartiesJoueur(partieJoueurEntities);

        List<PartieEntity> parties = new ArrayList<>();
        parties.add(partieEntity);

        DonnantDonnantAleatoireStrategie strategie = new DonnantDonnantAleatoireStrategie();
        CoupEnum coup = strategie.getCoup(parties);

        assertEquals(CoupEnum.TRAHIR, coup);
    }

    @Test
    void testGetCoupSansCoupAdversaire() {
        // Créer une partie sans coup de l'adversaire
        PartieEntity partieEntity = new PartieEntity();
        partieEntity.setPartiesJoueur(new ArrayList<>()); // Pas de joueurs

        List<PartieEntity> parties = new ArrayList<>();
        parties.add(partieEntity);

        DonnantDonnantAleatoireStrategie strategie = new DonnantDonnantAleatoireStrategie();
        CoupEnum coup = strategie.getCoup(parties);

        // Vérifier que le coup est aléatoire
        assertNotNull(coup); // Le coup doit être valide
        assertTrue(coup == CoupEnum.TRAHIR || coup == CoupEnum.COOPERER); // Doit être un coup valide
    }

    @Test
    void testProbabiliteCoupAleatoire() {
        // Créer une partie avec un coup de l'adversaire
        PartieJoueurEntity adversaire = new PartieJoueurEntity();
        adversaire.setCoup(CoupEnum.TRAHIR); // Simuler un coup de trahison

        List<PartieJoueurEntity> partieJoueurEntities = new ArrayList<>();
        partieJoueurEntities.add(adversaire);

        PartieEntity partieEntity = new PartieEntity();
        partieEntity.setPartiesJoueur(partieJoueurEntities);

        List<PartieEntity> parties = new ArrayList<>();
        parties.add(partieEntity);

        DonnantDonnantAleatoireStrategie strategie = new DonnantDonnantAleatoireStrategie();

        // Compter les coups aléatoires sur plusieurs essais
        int randomCoupCount = 0;
        int totalTests = 1000;

        for (int i = 0; i < totalTests; i++) {
            CoupEnum coup = strategie.getCoup(parties);
            if (coup != CoupEnum.TRAHIR) {
                randomCoupCount++;
            }
        }

        // Vérifier que le nombre de coups aléatoires est raisonnable
        double randomProbability = (double) randomCoupCount / totalTests;
        assertTrue(randomProbability < 0.3); // Moins de 30% de coups aléatoires
    }
}