package fr.uga.m1miage.pc.joueur.strategies;

import fr.uga.m1miage.pc.partie.enums.CoupEnum;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertTrue;

class AleatoireStrategieTest {


    @Test
    void testGetCoupReturnsValidMove() {
        AleatoireStrategie strategie = new AleatoireStrategie();
        CoupEnum coup = strategie.getCoup(Collections.emptyList());
        assertTrue(coup == CoupEnum.COOPERER || coup == CoupEnum.TRAHIR);
    }
}
