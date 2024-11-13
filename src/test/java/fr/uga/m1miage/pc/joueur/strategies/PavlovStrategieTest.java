package fr.uga.m1miage.pc.joueur.strategies;

import fr.uga.m1miage.pc.partie.enums.CoupEnum;
import fr.uga.m1miage.pc.partie.models.PartieEntity;
import fr.uga.m1miage.pc.partie.models.PartieJoueurEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PavlovStrategieTest {

    private PavlovStrategie pavlovStrategie;

    @BeforeEach
    public void setUp() {
        pavlovStrategie = new PavlovStrategie();
    }

    @Test
     void testPremierCoup() {
        List<PartieEntity> parties = new ArrayList<>();
        CoupEnum coup = pavlovStrategie.getCoup(parties);
        assertEquals(CoupEnum.COOPERER, coup);
    }

    @Test
    void testRepetitionCoup() {
        List<PartieEntity> parties = new ArrayList<>();

        // Simuler un coup précédent
        PartieEntity partie = new PartieEntity();
        PartieJoueurEntity partieJoueur = new PartieJoueurEntity();
        partieJoueur.setScore(5); // Score du dernier tour
        partieJoueur.setCoup(CoupEnum.COOPERER); // Dernier coup de l 'adversaire
        partie.setPartiesJoueur(List.of(partieJoueur));
        parties.add(partie);

        // Premier coup après le score de 5
        CoupEnum coup = pavlovStrategie.getCoup(parties);
        assertEquals(CoupEnum.COOPERER, coup); // Doit répéter le dernier coup
    }

    @Test
     void testCoupAdversaireCooperer() {
        List<PartieEntity> parties = new ArrayList<>();

        // Simuler un coup précédent
        PartieEntity partie = new PartieEntity();
        PartieJoueurEntity partieJoueur = new PartieJoueurEntity();
        partieJoueur.setScore(2); // Score du dernier tour
        partieJoueur.setCoup(CoupEnum.COOPERER); // Dernier coup de l'adversaire
        partie.setPartiesJoueur(List.of(partieJoueur));
        parties.add(partie);

        // Vérifier le coup après que l'adversaire a coopéré
        CoupEnum coup = pavlovStrategie.getCoup(parties);
        assertEquals(CoupEnum.COOPERER, coup); // Doit coopérer
    }

    @Test
     void testCoupAdversaireTrahir() {
        List<PartieEntity> parties = new ArrayList<>();

        // Simuler un coup précédent
        PartieEntity partie = new PartieEntity();
        PartieJoueurEntity partieJoueur = new PartieJoueurEntity();
        partieJoueur.setScore(2); // Score du dernier tour
        partieJoueur.setCoup(CoupEnum.TRAHIR); // Dernier coup de l'adversaire
        partie.setPartiesJoueur(List.of(partieJoueur));
        parties.add(partie);

        // Vérifier le coup après que l'adversaire a trahi
        CoupEnum coup = pavlovStrategie.getCoup(parties);
        assertEquals(CoupEnum.TRAHIR, coup); // Doit trahir
    }
}