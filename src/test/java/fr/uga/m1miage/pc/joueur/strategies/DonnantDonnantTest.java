package fr.uga.m1miage.pc.joueur.strategies;

import fr.uga.m1miage.pc.joueur.models.JoueurEntity;
import fr.uga.m1miage.pc.partie.enums.CoupEnum;
import fr.uga.m1miage.pc.partie.enums.StatutPartieEnum;
import fr.uga.m1miage.pc.partie.models.PartieEntity;
import fr.uga.m1miage.pc.partie.models.PartieJoueurEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.TestConfiguration;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestConfiguration
@ExtendWith(MockitoExtension.class)
class DonnantDonnantTest {

    @Mock
    private DonnantDonnant strategie;
    @Mock
    private List<PartieEntity> parties;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialise les mocks
        strategie = new DonnantDonnant();
        parties = new ArrayList<>();

    }

    @Test
    void testGetCoup_AucunePartiePrecedente() {
        // Cas où il n'y a pas de partie précédente
        PartieEntity partieEnCours = new PartieEntity();
        partieEnCours.setStatut(StatutPartieEnum.EN_COURS);
        partieEnCours.setOrdre(1);
        parties.add(partieEnCours);
        CoupEnum coup = strategie.getCoup(parties);
        assertEquals(CoupEnum.COOPERER, coup);
    }

    @Test
    void testGetCoup_PartiePrecedenteSansJoueurAdverse() {
        // Cas où la partie précédente n'a pas de joueur adverse
        PartieEntity partieEnCours = new PartieEntity();
        partieEnCours.setStatut(StatutPartieEnum.EN_COURS);
        partieEnCours.setOrdre(2);
        PartieEntity precedentePartie = new PartieEntity();
        precedentePartie.setOrdre(1);
        precedentePartie.setStatut(StatutPartieEnum.TERMINE);
        precedentePartie.setPartiesJoueur(new ArrayList<>());
        parties.add(precedentePartie);
        parties.add(partieEnCours);

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            strategie.getCoup(parties);
        });
        assertEquals("Aucun joueur adverse trouve", exception.getMessage());
    }

    @Test
    void testGetCoup_PartiePrecedenteAvecJoueurAdverse() {
        PartieEntity partieEnCours = new PartieEntity();
        partieEnCours.setStatut(StatutPartieEnum.EN_COURS);
        partieEnCours.setOrdre(2);

        PartieEntity precedentePartie = new PartieEntity();
        precedentePartie.setOrdre(1);
        precedentePartie.setStatut(StatutPartieEnum.EN_COURS); // Assurez-vous que la partie précédente est en cours

        JoueurEntity joueurAdverse = new JoueurEntity();
        joueurAdverse.setNomJoueur("Adversaire");

        PartieJoueurEntity partieJoueurAdverse = PartieJoueurEntity
                .builder()
                .joueur(joueurAdverse)
                .coup(CoupEnum.TRAHIR)
                .build();

        precedentePartie.setPartiesJoueur(List.of(partieJoueurAdverse));

        parties.add(precedentePartie);
        parties.add(partieEnCours);

        // Appel de la méthode
        String coup = String.valueOf(strategie.getCoup(parties));

        // Vérifiez le coup retourné
        assertEquals("COOPERER", coup);
    }



    @Test
    void testJoueursAvecAbandonNull() {
        // Créer un joueur adverse avec un état d'abandon null
        JoueurEntity joueurAdverse = JoueurEntity
                .builder()
                .abandon(null)
                .build();

        // Créer une instance de PartieJoueurEntity pour le joueur adverse
        PartieJoueurEntity partieJoueurAdverse = PartieJoueurEntity
                .builder()
                .joueur(joueurAdverse)
                .coup(CoupEnum.TRAHIR)
                .build();

        List<PartieJoueurEntity> partieJoueurs = new ArrayList<>();
        partieJoueurs.add(partieJoueurAdverse);

        // Créer une partie précédente avec le joueur adverse
        PartieEntity precedentePartie = PartieEntity
                .builder()
                .ordre(1)
                .statut(StatutPartieEnum.EN_COURS)
                .partiesJoueur(partieJoueurs)
                .build();


        // Créer une liste de parties et ajouter la partie précédente
        List<PartieEntity> parties = new ArrayList<>();
        parties.add(precedentePartie);

        // Simuler la récupération de la partie en cours

        // Vérifiez que le coup est retourné correctement
        CoupEnum coup = strategie.getCoup(parties);
        assertEquals(CoupEnum.COOPERER, coup);
    }
}