package fr.uga.m1miage.pc.serv.strategies;


import fr.uga.m1miage.pc.restApi.enums.CoupEnum;
import fr.uga.m1miage.pc.serv.models.JoueurEntity;
import fr.uga.m1miage.pc.serv.models.PartieEntity;
import fr.uga.m1miage.pc.serv.models.PartieJoueurEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class RancunierStrategieTest {

    @Test
    void testGetCoupAvecCoupTrahir() {
        JoueurEntity joueur = new JoueurEntity();
        joueur.setAbandon(null);

        PartieJoueurEntity partieJoueurEntity = new PartieJoueurEntity();
        partieJoueurEntity.setJoueur(joueur);
        partieJoueurEntity.setCoup(CoupEnum.TRAHIR);

        List<PartieJoueurEntity> partieJoueurEntities = new ArrayList<>();
        partieJoueurEntities.add(partieJoueurEntity);

        PartieEntity partieEntity = new PartieEntity();
        partieEntity.setPartiesJoueur(partieJoueurEntities);

        List<PartieEntity> parties = new ArrayList<>();
        parties.add(partieEntity);

        RancunierStrategie strategie = new RancunierStrategie();
        CoupEnum coup = strategie.getCoup(parties);

        Assertions.assertEquals(CoupEnum.TRAHIR, coup);
    }

    @Test
    void testGetCoupSansCoupTrahir() {
        PartieJoueurEntity partieJoueurEntity = new PartieJoueurEntity();
        partieJoueurEntity.setCoup(CoupEnum.COOPERER);

        partieJoueurEntity.setJoueur(new JoueurEntity());
        List<PartieJoueurEntity> partieJoueurEntities = new ArrayList<>();
        partieJoueurEntities.add(partieJoueurEntity);

        PartieEntity partieEntity = new PartieEntity();
        partieEntity.setPartiesJoueur(partieJoueurEntities);

        List<PartieEntity> parties = new ArrayList<>();
        parties.add(partieEntity);

        RancunierStrategie strategie = new RancunierStrategie();
        CoupEnum coup = strategie.getCoup(parties);

        Assertions.assertEquals(CoupEnum.COOPERER, coup);
    }

    @Test
    void testGetCoupAvecPartiesVides() {
        List<PartieEntity> parties = new ArrayList<>();

        RancunierStrategie strategie = new RancunierStrategie();
        CoupEnum coup = strategie.getCoup(parties);

        Assertions.assertEquals(CoupEnum.COOPERER, coup);
    }
}