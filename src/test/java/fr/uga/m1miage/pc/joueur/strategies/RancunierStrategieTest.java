package fr.uga.m1miage.pc.joueur.strategies;

import static org.junit.jupiter.api.Assertions.*;
import fr.uga.m1miage.pc.partie.enums.CoupEnum;
import fr.uga.m1miage.pc.partie.models.PartieEntity;
import fr.uga.m1miage.pc.partie.models.PartieJoueurEntity;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class RancunierStrategieTest {

    @Test
    void testGetCoupAvecCoupTrahir() {
        // Créer une partie avec un coup de trahison
        PartieJoueurEntity partieJoueurEntity = new PartieJoueurEntity();
        partieJoueurEntity.setCoup(CoupEnum.TRAHIR); // Simuler un coup de trahison

        List<PartieJoueurEntity> partieJoueurEntities = new ArrayList<>();
        partieJoueurEntities.add(partieJoueurEntity);

        PartieEntity partieEntity = new PartieEntity();
        partieEntity.setPartiesJoueur(partieJoueurEntities);

        List<PartieEntity> parties = new ArrayList<>();
        parties.add(partieEntity);

        RancunierStrategie strategie = new RancunierStrategie();
        CoupEnum coup = strategie.getCoup(parties);

        assertEquals(CoupEnum.COOPERER, coup); // Doit coopérer car un coup de trahison a été joué
    }

    @Test
    void testGetCoupSansCoupTrahir() {
        // Créer une partie sans coup de trahison
        PartieJoueurEntity partieJoueurEntity = new PartieJoueurEntity();
        partieJoueurEntity.setCoup(CoupEnum.COOPERER); // Simuler un coup de coopération

        List<PartieJoueurEntity> partieJoueurEntities = new ArrayList<>();
        partieJoueurEntities.add(partieJoueurEntity);

        PartieEntity partieEntity = new PartieEntity();
        partieEntity.setPartiesJoueur(partieJoueurEntities);

        List<PartieEntity> parties = new ArrayList<>();
        parties.add(partieEntity);

        RancunierStrategie strategie = new RancunierStrategie();
        CoupEnum coup = strategie.getCoup(parties);

        assertEquals(CoupEnum.TRAHIR, coup); // Doit trahir car aucun coup de trahison n'a été joué
    }

    @Test
    void testGetCoupAvecPartiesVides() {
        // Tester avec une liste de parties vide
        List<PartieEntity> parties = new ArrayList<>();

        RancunierStrategie strategie = new RancunierStrategie();
        CoupEnum coup = strategie.getCoup(parties);

        assertEquals(CoupEnum.TRAHIR, coup);
    }
}