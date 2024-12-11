package fr.uga.m1miage.pc.jeu.strategies;

import fr.uga.m1miage.pc.jeu.enums.CoupEnum;
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
