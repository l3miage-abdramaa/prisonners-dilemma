package fr.uga.m1miage.pc.serv.strategies;


import fr.uga.m1miage.pc.restApi.enums.StrategieEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StrategieFactoryTest {

    @Test
    void testGetStrategieToujoursTrahir() {
        StrategieInterface strategie = StrategieFactory.getStrategie(StrategieEnum.TOUJOURS_TRAHIR);
        Assertions.assertNotNull(strategie);
        Assertions.assertTrue(strategie instanceof ToujoursTrahirStrategie);
    }

    @Test
    void testGetStrategieToujoursCooperer() {
        StrategieInterface strategie = StrategieFactory.getStrategie(StrategieEnum.TOUJOURS_COOPERER);
        Assertions.assertNotNull(strategie);
        Assertions.assertTrue(strategie instanceof ToujoursCoopererStrategie);
    }

    @Test
    void testGetStrategieAleatoire() {
        StrategieInterface strategie = StrategieFactory.getStrategie(StrategieEnum.ALEATOIRE);
        Assertions.assertNotNull(strategie);
        Assertions.assertTrue(strategie instanceof AleatoireStrategie);
    }

    @Test
    void testGetStrategieDonnantDonnant() {
        StrategieInterface strategie = StrategieFactory.getStrategie(StrategieEnum.DONNANT_DONNANT);
        Assertions.assertNotNull(strategie);
        Assertions.assertTrue(strategie instanceof DonnantDonnantStrategie);
    }

    @Test
    void testGetStrategieRancunier() {
        StrategieInterface strategie = StrategieFactory.getStrategie(StrategieEnum.RANCUNIER);
        Assertions.assertNotNull(strategie);
        Assertions.assertTrue(strategie instanceof RancunierStrategie);
    }

    @Test
    void testGetStrategieDonnantDonnantAleatoire() {
        StrategieInterface strategie = StrategieFactory.getStrategie(StrategieEnum.DONNANT_DONNANT_ALEATOIRE);
        Assertions.assertNotNull(strategie);
        Assertions.assertTrue(strategie instanceof DonnantDonnantAleatoireStrategie);
    }




    @Test
    void testGetStrategieDonnantPourDeuxDonnantsAleatoire() {
        StrategieInterface strategie = StrategieFactory.getStrategie(StrategieEnum.DONNANT_POUR_DEUX_DONNANTS_ALEATOIRE);
        Assertions.assertNotNull(strategie);
        Assertions.assertTrue(strategie instanceof DonnantPourDeuxDonnantsAleatoireStrategie);
    }



    @Test
    void testGetStrategieSondeurNaif() {
        StrategieInterface strategie = StrategieFactory.getStrategie(StrategieEnum.SONDEUR_NAIF);
        Assertions.assertNotNull(strategie);
        Assertions.assertTrue(strategie instanceof SondeurNaifStrategie);
    }

    @Test
    void testGetStrategieSondeurRepentant() {
        StrategieInterface strategie = StrategieFactory.getStrategie(StrategieEnum.SONDEUR_REPENTANT);
        Assertions.assertNotNull(strategie);
        Assertions.assertTrue(strategie instanceof SondeurRepentantStrategie);
    }

    @Test
    void testGetStrategiePacificateurNaif() {
        StrategieInterface strategie = StrategieFactory.getStrategie(StrategieEnum.PACIFICATEUR_NAIF);
        Assertions.assertNotNull(strategie);
        Assertions.assertInstanceOf(PacificateurNaifStrategie.class, strategie);
    }


    @Test
    void testGetStrategieVraiPacificateur() {
        StrategieInterface strategie = StrategieFactory.getStrategie(StrategieEnum.VRAI_PACIFICATEUR);
        Assertions.assertNotNull(strategie);
        Assertions.assertInstanceOf(VraiPacificateurStrategie.class, strategie);
    }


    @Test
    void testGetStrategiePavlov() {
        StrategieInterface strategie = StrategieFactory.getStrategie(StrategieEnum.PAVLOV);
        Assertions.assertNotNull(strategie);
        Assertions.assertInstanceOf(PavlovStrategie.class, strategie);
    }

    @Test
    void testGetStrategiePavlovAleatoire() {
        StrategieInterface strategie = StrategieFactory.getStrategie(StrategieEnum.PAVLOV_ALEATOIRE);
        Assertions.assertNotNull(strategie);
        Assertions.assertInstanceOf(PavlovAleatoireStrategie.class, strategie);
    }

    @Test
    void testGetStrategieAdaptatif() {
        StrategieInterface strategie = StrategieFactory.getStrategie(StrategieEnum.ADAPTATIF);
        Assertions.assertNotNull(strategie);
        Assertions.assertInstanceOf(AdaptatifStrategie.class, strategie);
    }


    @Test
    void testGetStrategieGraduel() {
        StrategieInterface strategie = StrategieFactory.getStrategie(StrategieEnum.GRADUEL);
        Assertions.assertNotNull(strategie);
        Assertions.assertInstanceOf(GraduelStrategie.class, strategie);
    }

    @Test
    void testGetStrategieDonnantDonnantSupconneux() {
        StrategieInterface strategie = StrategieFactory.getStrategie(StrategieEnum.DONNANT_DONNANT_SOUPCONNEUX);
        Assertions.assertNotNull(strategie);
        Assertions.assertInstanceOf(DonnantDonnantSoupconneuxStrategie.class, strategie);
    }



    @Test
    void testGetStrategieRancunieuxDoux() {
        StrategieInterface strategie = StrategieFactory.getStrategie(StrategieEnum.RANCUNIEUX_DOUX);
        Assertions.assertNotNull(strategie);
        Assertions.assertInstanceOf(RancunierDouxStrategie.class, strategie);
    }


    @Test
    void testGetStrategieDonnantPourDeuxDonnants() {
        StrategieInterface strategie = StrategieFactory.getStrategie(StrategieEnum.DONNANT_POUR_DEUX_DONNANTS);
        Assertions.assertNotNull(strategie);
        Assertions.assertInstanceOf(DonnantPourDeuxDonnantsStrategie.class, strategie);
    }

    @Test
    void testGetStrategieAleatoire_G02_7() {
        StrategieInterface strategie = StrategieFactory.getStrategie(StrategieEnum.ALEATOIRE_G02_7);
        Assertions.assertNotNull(strategie);
        Assertions.assertInstanceOf(StrategieBinomeAdapter.class, strategie);
    }

    @Test
    void testGetStrategieTOUJOURS_TRAHIR_G02_7() {
        StrategieInterface strategie = StrategieFactory.getStrategie(StrategieEnum.TOUJOURS_TRAHIR_G02_7);
        Assertions.assertNotNull(strategie);
        Assertions.assertInstanceOf(StrategieBinomeAdapter.class, strategie);
    }

    @Test
    void testGetStrategieInconnue() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            StrategieFactory.getStrategie(null);
        });
        String expectedMessage = "Strategie inconnue : null";
        String actualMessage = exception.getMessage();
        System.out.println("MESSAGE : "+actualMessage);
        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }




}