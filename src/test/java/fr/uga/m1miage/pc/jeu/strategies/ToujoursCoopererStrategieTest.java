package fr.uga.m1miage.pc.jeu.strategies;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import fr.uga.m1miage.pc.jeu.enums.CoupEnum;


import java.util.Collections;

class ToujoursCoopererStrategieTest {

    @Test
    void testToujoursCooperer() {
        ToujoursCoopererStrategie strategie = new ToujoursCoopererStrategie();

        CoupEnum coup = strategie.getCoup(Collections.emptyList());

        assertEquals(CoupEnum.COOPERER, coup);
    }
}