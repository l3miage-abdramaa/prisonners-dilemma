package fr.uga.m1miage.pc.joueur.strategies;

import fr.uga.m1miage.pc.partie.enums.CoupEnum;
import fr.uga.m1miage.pc.partie.models.PartieEntity;
import fr.uga.m1miage.pc.partie.models.PartieJoueurEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PacificateurNaifStrategieTest {

    private PacificateurNaifStrategie strategie;
    private List<PartieEntity> parties;

    @BeforeEach
    public void setUp() {
        strategie = new PacificateurNaifStrategie();
        parties = new ArrayList<>();
    }

    @Test
    void testGetCoupCooperationAdversaire() {
        ajouterCoupAdversaire(CoupEnum.COOPERER);

        CoupEnum coup = strategie.getCoup(parties);
        assertEquals(CoupEnum.COOPERER, coup);
    }

    @Test
    void testGetCoupTrahisonAdversaire() {
        ajouterCoupAdversaire(CoupEnum.TRAHIR);
        CoupEnum coup = strategie.getCoup(parties);
        assertTrue(coup == CoupEnum.COOPERER || coup == CoupEnum.TRAHIR);
    }

    @Test
    void testGetCoupSansCoupPrecedent() {
        CoupEnum coup = strategie.getCoup(parties);
        assertTrue(coup == CoupEnum.COOPERER || coup == CoupEnum.TRAHIR);

    }

    private void ajouterCoupAdversaire(CoupEnum coup) {
        PartieEntity partie = new PartieEntity();
        PartieJoueurEntity joueur = new PartieJoueurEntity();
        joueur.setCoup(coup);
        List<PartieJoueurEntity> joueurs = new ArrayList<>();
        joueurs.add(joueur);
        partie.setPartiesJoueur(joueurs);
        parties.add(partie);
    }
}