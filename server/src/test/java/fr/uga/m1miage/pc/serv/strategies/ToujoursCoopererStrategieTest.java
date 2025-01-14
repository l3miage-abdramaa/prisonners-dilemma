package fr.uga.m1miage.pc.serv.strategies;


import fr.uga.m1miage.pc.restApi.enums.CoupEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;



import java.util.Collections;

class ToujoursCoopererStrategieTest {

    @Test
    void testToujoursCooperer() {
        ToujoursCoopererStrategie strategie = new ToujoursCoopererStrategie();

        CoupEnum coup = strategie.getCoup(Collections.emptyList());

        Assertions.assertEquals(CoupEnum.COOPERER, coup);
    }
}