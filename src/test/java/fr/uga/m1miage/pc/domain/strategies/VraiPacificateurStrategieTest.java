package fr.uga.m1miage.pc.domain.strategies;

import fr.uga.m1miage.pc.domain.enums.CoupEnum;
import fr.uga.m1miage.pc.domain.model.PartieEntity;
import fr.uga.m1miage.pc.domain.model.PartieJoueurEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class VraiPacificateurStrategieTest {

    private VraiPacificateurStrategie strategie;
    private List<PartieEntity> parties;

    @BeforeEach
    public void setUp() {
        strategie = new VraiPacificateurStrategie();
        parties = new ArrayList<>();
    }

    @Test
    void testPremierCoup() {

        CoupEnum coup = strategie.getCoup(parties);
        assertEquals(CoupEnum.COOPERER, coup);
    }

    @Test
    void testCoupAdversaireCooperer() {

        ajouterCoupAdversaire(CoupEnum.COOPERER);
        CoupEnum coup = strategie.getCoup(parties);
        assertEquals(CoupEnum.COOPERER, coup);
    }

    @Test
    void testCoupAdversaireTrahirUneFois() {

        ajouterCoupAdversaire(CoupEnum.TRAHIR);
        CoupEnum coup = strategie.getCoup(parties);

        assertTrue(coup == CoupEnum.COOPERER || coup == CoupEnum.TRAHIR);
    }

    @Test
    void testCoupAdversaireTrahirDeuxFois() {

        ajouterCoupAdversaire(CoupEnum.TRAHIR);
        strategie.getCoup(parties);
        CoupEnum coup = strategie.getCoup(parties);
        assertEquals(CoupEnum.TRAHIR, coup);
    }

    private void ajouterCoupAdversaire(CoupEnum coup) {
        PartieEntity partie = new PartieEntity();
        PartieJoueurEntity joueur = new PartieJoueurEntity();
        joueur.setCoup(coup);

        if (partie.getPartiesJoueur() == null) {
            partie.setPartiesJoueur(new ArrayList<>());
        }
        partie.getPartiesJoueur().add(joueur);
        parties.add(partie);
    }
}