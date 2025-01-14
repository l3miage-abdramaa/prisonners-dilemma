package fr.uga.m1miage.pc.serv.strategies;


import fr.uga.m1miage.pc.restApi.enums.CoupEnum;
import fr.uga.m1miage.pc.serv.models.PartieEntity;
import fr.uga.m1miage.pc.serv.models.PartieJoueurEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class DonnantPourDeuxDonnantsAleatoireStrategieTest {

    @Test
    void testCoupAleatoire() {

        DonnantPourDeuxDonnantsAleatoireStrategie strategie = new DonnantPourDeuxDonnantsAleatoireStrategie();

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
            } else if (coup == CoupEnum.TRAHIR) {
                trahirCount++;
            }
        }
        Assertions.assertTrue(coopererCount > 0 || trahirCount > 0, "La stratégie ne produit aucun coup aléatoire.");
    }


    @Test
    void testCoupAdverseRepetition() {
        DonnantPourDeuxDonnantsAleatoireStrategie strategie = new DonnantPourDeuxDonnantsAleatoireStrategie();
        List<PartieJoueurEntity> partiesJoueurs = new ArrayList<>();
        PartieJoueurEntity partieJoueur1 = PartieJoueurEntity.builder().coup(CoupEnum.COOPERER).build();
        PartieJoueurEntity partieJoueur2 = PartieJoueurEntity.builder().coup(CoupEnum.COOPERER).build();
        partiesJoueurs.add(partieJoueur1);
        partiesJoueurs.add(partieJoueur2);
        PartieEntity partie = PartieEntity.builder().partiesJoueur(partiesJoueurs).build();
        List<PartieEntity> parties = new ArrayList<>();
        parties.add(partie);
        CoupEnum coup = strategie.getCoup(parties);
        Assertions.assertEquals(coup,CoupEnum.COOPERER);

    }




}
