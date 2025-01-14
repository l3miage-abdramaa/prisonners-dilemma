package fr.uga.m1miage.pc.serv.strategies;

import fr.uga.m1miage.pc.restApi.enums.CoupEnum;
import fr.uga.m1miage.pc.serv.models.PartieEntity;
import fr.uga.m1miage.pc.serv.models.PartieJoueurEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PavlovStrategieTest {

    private PavlovStrategie pavlovStrategie;

    @BeforeEach
    public void setUp() {
        pavlovStrategie = new PavlovStrategie();
    }

    @Test
     void testPremierCoup() {
        List<PartieEntity> parties = new ArrayList<>();
        CoupEnum coup = pavlovStrategie.getCoup(parties);
        Assertions.assertEquals(CoupEnum.COOPERER, coup);
    }

    @Test
    void testRepetitionCoup() {
        List<PartieEntity> parties = new ArrayList<>();

        PartieEntity partie = new PartieEntity();
        PartieJoueurEntity partieJoueur = new PartieJoueurEntity();
        partieJoueur.setScore(5);
        partieJoueur.setCoup(CoupEnum.COOPERER);
        partie.setPartiesJoueur(List.of(partieJoueur));
        parties.add(partie);

        CoupEnum coup = pavlovStrategie.getCoup(parties);
        Assertions.assertEquals(CoupEnum.COOPERER, coup);
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

        CoupEnum coup = pavlovStrategie.getCoup(parties);
        Assertions.assertEquals(CoupEnum.COOPERER, coup);
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

        CoupEnum coup = pavlovStrategie.getCoup(parties);
        Assertions.assertEquals(CoupEnum.TRAHIR, coup);
    }
}