package fr.uga.m1miage.pc.jeu.strategies;

import fr.uga.m1miage.pc.jeu.models.JoueurEntity;
import fr.uga.m1miage.pc.jeu.enums.CoupEnum;
import fr.uga.m1miage.pc.jeu.enums.StatutPartieEnum;
import fr.uga.m1miage.pc.jeu.models.PartieEntity;
import fr.uga.m1miage.pc.jeu.models.PartieJoueurEntity;
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
class DonnantDonnantStrategieTest {

    @Mock
    private DonnantDonnantStrategie strategie;
    @Mock
    private List<PartieEntity> parties;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        strategie = new DonnantDonnantStrategie();
        parties = new ArrayList<>();

    }

    @Test
    void testGetCoup_AucunePartiePrecedente() {
        PartieEntity partieEnCours = new PartieEntity();
        partieEnCours.setStatut(StatutPartieEnum.EN_COURS);
        partieEnCours.setOrdre(1);
        parties.add(partieEnCours);
        CoupEnum coup = strategie.getCoup(parties);
        assertEquals(CoupEnum.COOPERER, coup);
    }

    @Test
    void testGetCoup_PartiePrecedenteSansJoueurAdverse() {
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
        precedentePartie.setStatut(StatutPartieEnum.EN_COURS);

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
        String coup = String.valueOf(strategie.getCoup(parties));

        assertEquals("COOPERER", coup);
    }



    @Test
    void testJoueursAvecAbandonNull() {
        JoueurEntity joueurAdverse = JoueurEntity
                .builder()
                .abandon(null)
                .build();

        PartieJoueurEntity partieJoueurAdverse = PartieJoueurEntity
                .builder()
                .joueur(joueurAdverse)
                .coup(CoupEnum.TRAHIR)
                .build();

        List<PartieJoueurEntity> partieJoueurs = new ArrayList<>();
        partieJoueurs.add(partieJoueurAdverse);

        PartieEntity precedentePartie = PartieEntity
                .builder()
                .ordre(1)
                .statut(StatutPartieEnum.EN_COURS)
                .partiesJoueur(partieJoueurs)
                .build();


        List<PartieEntity> partiesEncours = new ArrayList<>();
        partiesEncours.add(precedentePartie);


        CoupEnum coup = strategie.getCoup(partiesEncours);
        assertEquals(CoupEnum.COOPERER, coup);
    }
}