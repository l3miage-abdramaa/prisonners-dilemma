package fr.uga.m1miage.pc.joueur.strategies;

import fr.uga.m1miage.pc.partie.enums.CoupEnum;
import fr.uga.m1miage.pc.partie.models.PartieEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AdaptatifStrategieTest {

    private AdaptatifStrategie adaptatifStrategie;

    @BeforeEach
    public void setUp() {
        adaptatifStrategie = new AdaptatifStrategie();
    }

    @Test
    void testPremierCoup() {
        List<PartieEntity> parties = new ArrayList<>();
        CoupEnum coup = adaptatifStrategie.getCoup(parties);
        assertEquals(CoupEnum.COOPERER, coup);
    }

    @Test
    void testSequenceInitiale() {
        List<PartieEntity> parties = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            CoupEnum coup = adaptatifStrategie.getCoup(parties);
            assertEquals(CoupEnum.COOPERER, coup);
        }
        for (int i = 0; i < 5; i++) {
            CoupEnum coup = adaptatifStrategie.getCoup(parties);
            assertEquals(CoupEnum.TRAHIR, coup);
        }
    }

    @Test
    void testChoixAdaptatif() {
        List<PartieEntity> parties = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            adaptatifStrategie.getCoup(parties);
        }
        for (int i = 0; i < 5; i++) {
            adaptatifStrategie.getCoup(parties);
        }

        CoupEnum coup = adaptatifStrategie.getCoup(parties);
        assertEquals(CoupEnum.COOPERER, coup);
    }

    @Test
     void testChoixAdaptatifAvecScoresSimules() {
        List<PartieEntity> parties = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            adaptatifStrategie.getCoup(parties);
        }

        for (int i = 0; i < 5; i++) {
            adaptatifStrategie.getCoup(parties);
        }

        adaptatifStrategie.getCoup(parties);

        CoupEnum coup = adaptatifStrategie.getCoup(parties);
        assertEquals(CoupEnum.COOPERER, coup);
    }
}