package fr.uga.m1miage.pc.joueur.strategies;

import static org.junit.jupiter.api.Assertions.*;

import fr.uga.m1miage.pc.joueur.models.JoueurEntity;
import fr.uga.m1miage.pc.partie.enums.CoupEnum;
import fr.uga.m1miage.pc.partie.models.PartieEntity;
import fr.uga.m1miage.pc.partie.models.PartieJoueurEntity;
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

        assertEquals(CoupEnum.TRAHIR, coup);
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

        assertEquals(CoupEnum.COOPERER, coup);
    }

    @Test
    void testGetCoupAvecPartiesVides() {
        List<PartieEntity> parties = new ArrayList<>();

        RancunierStrategie strategie = new RancunierStrategie();
        CoupEnum coup = strategie.getCoup(parties);

        assertEquals(CoupEnum.COOPERER, coup);
    }
}