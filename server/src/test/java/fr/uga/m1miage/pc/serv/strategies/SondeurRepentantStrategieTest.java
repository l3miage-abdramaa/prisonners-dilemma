package fr.uga.m1miage.pc.serv.strategies;


import fr.uga.m1miage.pc.restApi.enums.CoupEnum;
import fr.uga.m1miage.pc.serv.models.PartieEntity;
import fr.uga.m1miage.pc.serv.models.PartieJoueurEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

class SondeurRepentantStrategieTest {

    private SondeurRepentantStrategie strategie;

    @BeforeEach
    public void setUp() {
        strategie = new SondeurRepentantStrategie();
    }

    @Test
     void testCoopererSiDernierCoupAdversaireEstTrahir() {
        List<PartieEntity> parties = new ArrayList<>();
        PartieEntity partie = Mockito.mock(PartieEntity.class);
        PartieJoueurEntity joueurAdversaire = Mockito.mock(PartieJoueurEntity.class);

        Mockito.when(joueurAdversaire.getCoup()).thenReturn(CoupEnum.TRAHIR);
        Mockito.when(partie.getPartiesJoueur()).thenReturn(List.of(joueurAdversaire));
        parties.add(partie);

        CoupEnum coup = strategie.getCoup(parties);

        Assertions.assertEquals(CoupEnum.COOPERER, coup);
    }


    @Test
    void testTrahirAvecProbabilite() {
        List<PartieEntity> parties = new ArrayList<>();
        PartieEntity partie = Mockito.mock(PartieEntity.class);
        PartieJoueurEntity joueurAdversaire = Mockito.mock(PartieJoueurEntity.class);

        Mockito.when(joueurAdversaire.getCoup()).thenReturn(CoupEnum.COOPERER);
        Mockito.when(partie.getPartiesJoueur()).thenReturn(List.of(joueurAdversaire));
        parties.add(partie);

        int trahisons = 0;
        int iterations = 10000;
        for (int i =  0; i < iterations; i++) {
            CoupEnum coup = strategie.getCoup(parties);
            if (coup == CoupEnum.TRAHIR) {
                trahisons++;
            }
        }

        Assertions.assertTrue(trahisons >= 2500 && trahisons <= 3500);
    }
}