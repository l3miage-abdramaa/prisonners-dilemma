package fr.uga.m1miage.pc.joueur.strategies;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import fr.uga.m1miage.pc.partie.enums.CoupEnum;
import fr.uga.m1miage.pc.partie.models.PartieEntity;
import fr.uga.m1miage.pc.partie.models.PartieJoueurEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        PartieEntity partie = mock(PartieEntity.class);
        PartieJoueurEntity joueurAdversaire = mock(PartieJoueurEntity.class);

        when(joueurAdversaire.getCoup()).thenReturn(CoupEnum.TRAHIR);
        when(partie.getPartiesJoueur()).thenReturn(List.of(joueurAdversaire));
        parties.add(partie);

        CoupEnum coup = strategie.getCoup(parties);

        assertEquals(CoupEnum.COOPERER, coup);
    }


    @Test
    void testTrahirAvecProbabilite() {
        List<PartieEntity> parties = new ArrayList<>();
        PartieEntity partie = mock(PartieEntity.class);
        PartieJoueurEntity joueurAdversaire = mock(PartieJoueurEntity.class);

        when(joueurAdversaire.getCoup()).thenReturn(CoupEnum.COOPERER);
        when(partie.getPartiesJoueur()).thenReturn(List.of(joueurAdversaire));
        parties.add(partie);

        int trahisons = 0;
        int iterations = 10000;
        for (int i =  0; i < iterations; i++) {
            CoupEnum coup = strategie.getCoup(parties);
            if (coup == CoupEnum.TRAHIR) {
                trahisons++;
            }
        }

        assertTrue(trahisons >= 2500 && trahisons <= 3500);
    }
}