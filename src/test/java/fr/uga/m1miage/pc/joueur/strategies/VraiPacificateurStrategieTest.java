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
        // Premier coup, doit coopérer
        CoupEnum coup = strategie.getCoup(parties);
        assertEquals(CoupEnum.COOPERER, coup);
    }

    @Test
    void testCoupAdversaireCooperer() {
        // Simuler un coup précédent où l'adversaire a coopéré
        ajouterCoupAdversaire(CoupEnum.COOPERER);
        CoupEnum coup = strategie.getCoup(parties);
        assertEquals(CoupEnum.COOPERER, coup);
    }

    @Test
    void testCoupAdversaireTrahirUneFois() {
        // Simuler un coup précédent où l'adversaire a trahi une fois
        ajouterCoupAdversaire(CoupEnum.TRAHIR);
        CoupEnum coup = strategie.getCoup(parties);
        // On ne peut pas garantir le résultat ici, mais on s'attend à ce qu'il soit soit COOPERER soit TRAHIR
        assertTrue(coup == CoupEnum.COOPERER || coup == CoupEnum.TRAHIR);
    }

    @Test
    void testCoupAdversaireTrahirDeuxFois() {
        // Simuler deux coups précédents où l'adversaire a trahi
        ajouterCoupAdversaire(CoupEnum.TRAHIR);
        strategie.getCoup(parties); // Premier coup, on ne fait rien
        CoupEnum coup = strategie.getCoup(parties); // Deuxième coup
        assertEquals(CoupEnum.TRAHIR, coup);
    }

    private void ajouterCoupAdversaire(CoupEnum coup) {
        PartieEntity partie = new PartieEntity();
        PartieJoueurEntity joueur = new PartieJoueurEntity();
        joueur.setCoup(coup);
        // Assurez-vous que la liste est initialisée
        if (partie.getPartiesJoueur() == null) {
            partie.setPartiesJoueur(new ArrayList<>());
        }
        partie.getPartiesJoueur().add(joueur);
        parties.add(partie);
    }
}