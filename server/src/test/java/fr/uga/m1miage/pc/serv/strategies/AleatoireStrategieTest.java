package fr.uga.m1miage.pc.serv.strategies;

import fr.uga.m1miage.pc.restApi.enums.CoupEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;


class AleatoireStrategieTest {


    @Test
    void testGetCoupReturnsValidMove() {
        AleatoireStrategie strategie = new AleatoireStrategie();
        CoupEnum coup = strategie.getCoup(Collections.emptyList());
        Assertions.assertTrue(coup == CoupEnum.COOPERER || coup == CoupEnum.TRAHIR);
    }
}
