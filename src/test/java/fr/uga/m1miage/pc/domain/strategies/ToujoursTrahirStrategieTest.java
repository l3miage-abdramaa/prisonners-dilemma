package fr.uga.m1miage.pc.domain.strategies;

import static org.junit.jupiter.api.Assertions.assertEquals;

import fr.uga.m1miage.pc.domain.enums.CoupEnum;
import org.junit.jupiter.api.Test;

import java.util.Collections;

class ToujoursTrahirStrategieTest {

    @Test
    void testToujoursTrahir() {
        ToujoursTrahirStrategie strategie = new ToujoursTrahirStrategie();

        CoupEnum coup = strategie.getCoup(Collections.emptyList());

        assertEquals(CoupEnum.TRAHIR, coup);
    }
}