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
        assertTrue(strategie instanceof DonnantDonnant);
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