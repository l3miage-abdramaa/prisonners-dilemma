package fr.uga.m1miage.pc.serv.strategies;


import fr.uga.m1miage.pc.restApi.enums.CoupEnum;
import fr.uga.m1miage.pc.serv.models.PartieEntity;
import fr.uga.m1miage.pc.serv.models.PartieJoueurEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class PavlovAleatoireStrategieTest {

    private PavlovAleatoireStrategie pavlovAleatoireStrategie;

    @BeforeEach
    public void setUp() {
        pavlovAleatoireStrategie = new PavlovAleatoireStrategie();
    }

    @Test
     void testPremierCoup() {
        List<PartieEntity> parties = new ArrayList<>();
        CoupEnum coup = pavlovAleatoireStrategie.getCoup(parties);
        Assertions.assertEquals(CoupEnum.COOPERER, coup);
    }

    @Test
    void testRepetitionCoupAvecScore5() {
        List<PartieEntity> parties = new ArrayList<>();

        PartieEntity partie = new PartieEntity();
        PartieJoueurEntity partieJoueur = new PartieJoueurEntity();
        partieJoueur.setScore(5);
        partieJoueur.setCoup(CoupEnum.COOPERER);
        partie.setPartiesJoueur(List.of(partieJoueur));
        parties.add(partie);

        CoupEnum coup = pavlovAleatoireStrategie.getCoup(parties);
        Assertions.assertTrue(coup == CoupEnum.COOPERER || coup == CoupEnum.TRAHIR);
    }

    @Test
    void testRepetitionCoupAvecScore3() {
        List<PartieEntity> parties = new ArrayList<>();

        PartieEntity partie = new PartieEntity();
        PartieJoueurEntity partieJoueur = new PartieJoueurEntity();
        partieJoueur.setScore(3);
        partieJoueur.setCoup(CoupEnum.TRAHIR);
        partie.setPartiesJoueur(List.of(partieJoueur));
        parties.add(partie);

        CoupEnum coup = pavlovAleatoireStrategie.getCoup(parties);
        Assertions.assertTrue(coup == CoupEnum.COOPERER || coup == CoupEnum.TRAHIR);
    }

    @Test
    void testCoupAdversaireCooperer() {
        List<PartieEntity> parties = new ArrayList<>();

        PartieEntity partie = new PartieEntity();
        PartieJoueurEntity partieJoueur = new PartieJoueurEntity();
        partieJoueur.setScore(2);
        partieJoueur.setCoup(CoupEnum.COOPERER);
        partie.setPartiesJoueur(List.of(partieJoueur));
        parties.add(partie);

        CoupEnum coup = pavlovAleatoireStrategie.getCoup(parties);
        Assertions.assertTrue(coup == CoupEnum.COOPERER || coup == CoupEnum.TRAHIR);
    }

    @Test
    void testCoupAdversaireTrahir() {
        List<PartieEntity> parties = new ArrayList<>();

        PartieEntity partie = new PartieEntity();
        PartieJoueurEntity partieJoueur = new PartieJoueurEntity();
        partieJoueur.setScore(2);
        partieJoueur.setCoup(CoupEnum.TRAHIR);
        partie.setPartiesJoueur(List.of(partieJoueur));
        parties.add(partie);

        CoupEnum coup = pavlovAleatoireStrategie.getCoup(parties);
        Assertions.assertTrue(coup == CoupEnum.COOPERER || coup == CoupEnum.TRAHIR);
    }
}