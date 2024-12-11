package fr.uga.m1miage.pc.jeu.strategies;

import fr.uga.m1miage.pc.jeu.models.JoueurEntity;
import fr.uga.m1miage.pc.jeu.enums.CoupEnum;
import fr.uga.m1miage.pc.jeu.models.PartieEntity;
import fr.uga.m1miage.pc.jeu.models.PartieJoueurEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GraduelStrategieTest {

    private GraduelStrategie strategie;
    private PartieEntity partie;

    @BeforeEach
    void setUp() {
        strategie = new GraduelStrategie();
        partie = new PartieEntity();
        partie.setPartiesJoueur(Collections.emptyList());
    }

    @Test
    void testCooperationInitiale() {
        PartieJoueurEntity adversaire = new PartieJoueurEntity();
        adversaire.setJoueur(new JoueurEntity());
        adversaire.setCoup(CoupEnum.COOPERER);
        partie.setPartiesJoueur(List.of(adversaire));

        CoupEnum coup = strategie.getCoup(List.of(partie));
        assertEquals(CoupEnum.COOPERER, coup);
        assertEquals(0, strategie.getTrahisonsAdversaire());
        assertEquals(0, strategie.getCoupsCooperationRestants());
    }

    @Test
    void testTrahisonUneFois() {
        PartieJoueurEntity adversaire = new PartieJoueurEntity();
        adversaire.setJoueur(new JoueurEntity());
        adversaire.setCoup(CoupEnum.TRAHIR);
        partie.setPartiesJoueur(List.of(adversaire));

        CoupEnum coup = strategie.getCoup(List.of(partie));
        assertEquals(CoupEnum.COOPERER, coup);
        assertEquals(1, strategie.getTrahisonsAdversaire());
    }

    @Test
    void testTrahisonDeuxFois() {
        PartieJoueurEntity adversaire = new PartieJoueurEntity();
        adversaire.setJoueur(new JoueurEntity());
        adversaire.setCoup(CoupEnum.TRAHIR);
        partie.setPartiesJoueur(List.of(adversaire));

        CoupEnum coup1 = strategie.getCoup(List.of(partie));
        assertEquals(CoupEnum.COOPERER, coup1);
        assertEquals(1, strategie.getTrahisonsAdversaire());

        adversaire.setCoup(CoupEnum.TRAHIR);
        CoupEnum coup2 = strategie.getCoup(List.of(partie));
        assertEquals(CoupEnum.TRAHIR, coup2);
        assertEquals(2, strategie.getCoupsCooperationRestants());
    }

    @Test
    void testCooperationApresTrahison() {
        PartieJoueurEntity adversaire = new PartieJoueurEntity();
        adversaire.setJoueur(new JoueurEntity());
        adversaire.setCoup(CoupEnum.TRAHIR);
        partie.setPartiesJoueur(List.of(adversaire));

        strategie.getCoup(List.of(partie));

        adversaire.setCoup(CoupEnum.COOPERER);

        CoupEnum coup = strategie.getCoup(List.of(partie));

        assertEquals(CoupEnum.COOPERER, coup);
        assertEquals(1, strategie.getTrahisonsAdversaire());
        assertEquals(1, strategie.getCoupsCooperationRestants());
    }


}