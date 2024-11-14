package fr.uga.m1miage.pc.joueur.strategies;

import static org.junit.jupiter.api.Assertions.*;

import fr.uga.m1miage.pc.joueur.enums.StrategieEnum;

import org.junit.jupiter.api.Test;

class StrategieFactoryTest {

    @Test
    void testGetStrategieToujoursTrahir() {
        StrategieInterface strategie = StrategieFactory.getStrategie(StrategieEnum.TOUJOURS_TRAHIR);
        assertNotNull(strategie);
        assertTrue(strategie instanceof ToujoursTrahirStrategie);
    }

    @Test
    void testGetStrategieToujoursCooperer() {
        StrategieInterface strategie = StrategieFactory.getStrategie(StrategieEnum.TOUJOURS_COOPERER);
        assertNotNull(strategie);
        assertTrue(strategie instanceof ToujoursCoopererStrategie);
    }

    @Test
    void testGetStrategieAleatoire() {
        StrategieInterface strategie = StrategieFactory.getStrategie(StrategieEnum.ALEATOIRE);
        assertNotNull(strategie);
        assertTrue(strategie instanceof AleatoireStrategie);
    }

    @Test
    void testGetStrategieDonnantDonnant() {
        StrategieInterface strategie = StrategieFactory.getStrategie(StrategieEnum.DONNANT_DONNANT);
        assertNotNull(strategie);
        assertTrue(strategie instanceof DonnantDonnantStrategie);
    }

    @Test
    void testGetStrategieRancunier() {
        StrategieInterface strategie = StrategieFactory.getStrategie(StrategieEnum.RANCUNIER);
        assertNotNull(strategie);
        assertTrue(strategie instanceof RancunierStrategie);
    }

    @Test
    void testGetStrategieDonnantDonnantAleatoire() {
        StrategieInterface strategie = StrategieFactory.getStrategie(StrategieEnum.DONNANT_DONNANT_ALEATOIRE);
        assertNotNull(strategie);
        assertTrue(strategie instanceof DonnantDonnantAleatoireStrategie);
    }




    @Test
    void testGetStrategieDonnantPourDeuxDonnantsAleatoire() {
        StrategieInterface strategie = StrategieFactory.getStrategie(StrategieEnum.DONNANT_POUR_DEUX_DONNANTS_ALEATOIRE);
        assertNotNull(strategie);
        assertTrue(strategie instanceof DonnantPourDeuxDonnantsAleatoireStrategie);
    }



    @Test
    void testGetStrategieSondeurNaif() {
        StrategieInterface strategie = StrategieFactory.getStrategie(StrategieEnum.SONDEUR_NAIF);
        assertNotNull(strategie);
        assertTrue(strategie instanceof SondeurNaifStrategie);
    }

    @Test
    void testGetStrategieSondeurRepentant() {
        StrategieInterface strategie = StrategieFactory.getStrategie(StrategieEnum.SONDEUR_REPENTANT);
        assertNotNull(strategie);
        assertTrue(strategie instanceof SondeurRepentantStrategie);
    }

    @Test
    void testGetStrategiePacificateurNaif() {
        StrategieInterface strategie = StrategieFactory.getStrategie(StrategieEnum.PACIFICATEUR_NAIF);
        assertNotNull(strategie);
        assertTrue(strategie instanceof PacificateurNaifStrategie);
    }


    @Test
    void testGetStrategieVraiPacificateur() {
        StrategieInterface strategie = StrategieFactory.getStrategie(StrategieEnum.VRAI_PACIFICATEUR);
        assertNotNull(strategie);
        assertTrue(strategie instanceof VraiPacificateurStrategie);
    }


    @Test
    void testGetStrategiePavlov() {
        StrategieInterface strategie = StrategieFactory.getStrategie(StrategieEnum.PAVLOV);
        assertNotNull(strategie);
        assertTrue(strategie instanceof PavlovStrategie);
    }

    @Test
    void testGetStrategiePavlovAleatoire() {
        StrategieInterface strategie = StrategieFactory.getStrategie(StrategieEnum.PAVLOV_ALEATOIRE);
        assertNotNull(strategie);
        assertTrue(strategie instanceof PavlovAleatoireStrategie);
    }

    @Test
    void testGetStrategieAdaptatif() {
        StrategieInterface strategie = StrategieFactory.getStrategie(StrategieEnum.ADAPTATIF);
        assertNotNull(strategie);
        assertTrue(strategie instanceof AdaptatifStrategie);
    }


    @Test
    void testGetStrategieGraduel() {
        StrategieInterface strategie = StrategieFactory.getStrategie(StrategieEnum.GRADUEL);
        assertNotNull(strategie);
        assertTrue(strategie instanceof GraduelStrategie);
    }

    @Test
    void testGetStrategieDonnantDonnantSupconneux() {
        StrategieInterface strategie = StrategieFactory.getStrategie(StrategieEnum.DONNANT_DONNANT_SOUPCONNEUX);
        assertNotNull(strategie);
        assertTrue(strategie instanceof DonnantDonnantSoupconneuxStrategie);
    }



    @Test
    void testGetStrategieRancunieuxDoux() {
        StrategieInterface strategie = StrategieFactory.getStrategie(StrategieEnum.RANCUNIEUX_DOUX);
        assertNotNull(strategie);
        assertTrue(strategie instanceof RancunierDouxStrategie);
    }

    @Test
    void testGetStrategieInconnue() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            StrategieFactory.getStrategie(null); // Tester avec une valeur nulle
        });
        String expectedMessage = "Strategie inconnue : null";
        String actualMessage = exception.getMessage();
        System.out.println("MESSAGE : "+actualMessage);
        assertTrue(actualMessage.contains(expectedMessage));
    }




}