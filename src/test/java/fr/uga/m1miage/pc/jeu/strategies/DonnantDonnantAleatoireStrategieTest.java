package fr.uga.m1miage.pc.jeu.strategies;

import static org.junit.jupiter.api.Assertions.*;

import fr.uga.m1miage.pc.jeu.enums.CoupEnum;
import fr.uga.m1miage.pc.jeu.models.PartieEntity;
import fr.uga.m1miage.pc.jeu.models.PartieJoueurEntity;
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

        assertTrue(coup == CoupEnum.COOPERER || coup == CoupEnum.TRAHIR);

    }

    @Test
    void testGetCoupSansCoupAdversaire() {
        PartieEntity partieEntity = new PartieEntity();
        partieEntity.setPartiesJoueur(new ArrayList<>());

        List<PartieEntity> parties = new ArrayList<>();
        parties.add(partieEntity);

        DonnantDonnantAleatoireStrategie strategie = new DonnantDonnantAleatoireStrategie();
        CoupEnum coup = strategie.getCoup(parties);

        assertNotNull(coup);
        assertTrue(coup == CoupEnum.TRAHIR || coup == CoupEnum.COOPERER);
    }

    @Test
    void testProbabiliteCoupAleatoire() {

        PartieJoueurEntity adversaire = new PartieJoueurEntity();
        adversaire.setCoup(CoupEnum.TRAHIR);

        List<PartieJoueurEntity> partieJoueurEntities = new ArrayList<>();
        partieJoueurEntities.add(adversaire);

        PartieEntity partieEntity = new PartieEntity();
        partieEntity.setPartiesJoueur(partieJoueurEntities);

        List<PartieEntity> parties = new ArrayList<>();
        parties.add(partieEntity);

        DonnantDonnantAleatoireStrategie strategie = new DonnantDonnantAleatoireStrategie();


        int randomCoupCount = 0;
        int totalTests = 1000;

        for (int i = 0; i < totalTests; i++) {
            CoupEnum coup = strategie.getCoup(parties);
            if (coup != CoupEnum.TRAHIR) {
                randomCoupCount++;
            }
        }


        double randomProbability = (double) randomCoupCount / totalTests;
        assertTrue(randomProbability < 0.3);
    }
}