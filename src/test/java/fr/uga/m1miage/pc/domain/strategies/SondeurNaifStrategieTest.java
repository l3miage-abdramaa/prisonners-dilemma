package fr.uga.m1miage.pc.domain.strategies;

import fr.uga.m1miage.pc.domain.enums.CoupEnum;
import fr.uga.m1miage.pc.domain.model.PartieEntity;
import fr.uga.m1miage.pc.domain.model.PartieJoueurEntity;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SondeurNaifStrategieTest {


    @Test
    void testCoupAleatoire() {
        SondeurNaifStrategie strategie = new SondeurNaifStrategie();

        List<PartieJoueurEntity> partiesJoueurs = new ArrayList<>();
        PartieJoueurEntity partieJoueur1 = PartieJoueurEntity.builder().coup(CoupEnum.COOPERER).build();
        partiesJoueurs.add(partieJoueur1);

        PartieEntity partie = PartieEntity.builder().partiesJoueur(partiesJoueurs).build();
        List<PartieEntity> parties = new ArrayList<>();
        parties.add(partie);

        int coopererCount = 0;
        int trahirCount = 0;

        for (int i = 0; i < 100; i++) {
            CoupEnum coup = strategie.getCoup(parties);
            if (coup == CoupEnum.COOPERER) {
                coopererCount++;
            } else {
                trahirCount++;
            }
        }

        assertTrue(coopererCount > 0 || trahirCount > 0);
    }

    @Test
    void testCoupReciprocite() {
        SondeurNaifStrategie strategie = new SondeurNaifStrategie();
        List<PartieJoueurEntity> partiesJoueurs = new ArrayList<>();

        PartieJoueurEntity partieJoueur1 = PartieJoueurEntity.builder().coup(CoupEnum.COOPERER).build();
        PartieJoueurEntity partieJoueur2 = PartieJoueurEntity.builder().coup(CoupEnum.TRAHIR).build();
        partiesJoueurs.add(partieJoueur1);
        partiesJoueurs.add(partieJoueur2);

        PartieEntity partie = PartieEntity.builder().partiesJoueur(partiesJoueurs).build();
        List<PartieEntity> parties = new ArrayList<>();
        parties.add(partie);

        CoupEnum coup = strategie.getCoup(parties);
        assertEquals(CoupEnum.TRAHIR, coup);
    }
}