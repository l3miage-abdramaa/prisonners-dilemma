package fr.uga.m1miage.pc.domain.strategies;

import static org.junit.jupiter.api.Assertions.*;

import fr.uga.m1miage.pc.domain.enums.StrategieEnum;
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
        assertInstanceOf(PacificateurNaifStrategie.class, strategie);
    }


    @Test
    void testGetStrategieVraiPacificateur() {
        StrategieInterface strategie = StrategieFactory.getStrategie(StrategieEnum.VRAI_PACIFICATEUR);
        assertNotNull(strategie);
        assertInstanceOf(VraiPacificateurStrategie.class, strategie);
    }


    @Test
    void testGetStrategiePavlov() {
        StrategieInterface strategie = StrategieFactory.getStrategie(StrategieEnum.PAVLOV);
        assertNotNull(strategie);
        assertInstanceOf(PavlovStrategie.class, strategie);
    }

    @Test
    void testGetStrategiePavlovAleatoire() {
        StrategieInterface strategie = StrategieFactory.getStrategie(StrategieEnum.PAVLOV_ALEATOIRE);
        assertNotNull(strategie);
        assertInstanceOf(PavlovAleatoireStrategie.class, strategie);
    }

    @Test
    void testGetStrategieAdaptatif() {
        StrategieInterface strategie = StrategieFactory.getStrategie(StrategieEnum.ADAPTATIF);
        assertNotNull(strategie);
        assertInstanceOf(AdaptatifStrategie.class, strategie);
    }


    @Test
    void testGetStrategieGraduel() {
        StrategieInterface strategie = StrategieFactory.getStrategie(StrategieEnum.GRADUEL);
        assertNotNull(strategie);
        assertInstanceOf(GraduelStrategie.class, strategie);
    }

    @Test
    void testGetStrategieDonnantDonnantSupconneux() {
        StrategieInterface strategie = StrategieFactory.getStrategie(StrategieEnum.DONNANT_DONNANT_SOUPCONNEUX);
        assertNotNull(strategie);
        assertInstanceOf(DonnantDonnantSoupconneuxStrategie.class, strategie);
    }



    @Test
    void testGetStrategieRancunieuxDoux() {
        StrategieInterface strategie = StrategieFactory.getStrategie(StrategieEnum.RANCUNIEUX_DOUX);
        assertNotNull(strategie);
        assertInstanceOf(RancunierDouxStrategie.class, strategie);
    }


    @Test
    void testGetStrategieDonnantPourDeuxDonnants() {
        StrategieInterface strategie = StrategieFactory.getStrategie(StrategieEnum.DONNANT_POUR_DEUX_DONNANTS);
        assertNotNull(strategie);
        assertInstanceOf(DonnantPourDeuxDonnantsStrategie.class, strategie);
    }

    @Test
    void testGetStrategieAleatoire_G02_7() {
        StrategieInterface strategie = StrategieFactory.getStrategie(StrategieEnum.ALEATOIRE_G02_7);
        assertNotNull(strategie);
        assertInstanceOf(StrategieBinomeAdapter.class, strategie);
    }

    @Test
    void testGetStrategieTOUJOURS_TRAHIR_G02_7() {
        StrategieInterface strategie = StrategieFactory.getStrategie(StrategieEnum.TOUJOURS_TRAHIR_G02_7);
        assertNotNull(strategie);
        assertInstanceOf(StrategieBinomeAdapter.class, strategie);
    }

    @Test
    void testGetStrategieInconnue() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            StrategieFactory.getStrategie(null);
        });
        String expectedMessage = "Strategie inconnue : null";
        String actualMessage = exception.getMessage();
        System.out.println("MESSAGE : "+actualMessage);
        assertTrue(actualMessage.contains(expectedMessage));
    }




}