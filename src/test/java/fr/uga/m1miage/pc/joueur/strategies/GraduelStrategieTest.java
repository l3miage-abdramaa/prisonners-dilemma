package fr.uga.m1miage.pc.joueur.strategies;
import fr.uga.m1miage.pc.joueur.models.JoueurEntity;
import fr.uga.m1miage.pc.partie.models.PartieEntity;
import fr.uga.m1miage.pc.partie.models.PartieJoueurEntity;
import fr.uga.m1miage.pc.partie.enums.CoupEnum;
import fr.uga.m1miage.pc.partie.enums.StatutPartieEnum;
import fr.uga.m1miage.pc.joueur.enums.StrategieEnum;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GraduelStrategieTest {

    @Test
    void testGraduelStrategieCooperationAfterBetrayal() {
        JoueurEntity joueur1 = JoueurEntity.builder()
                .nomJoueur("Joueur 1")
                .strategie(StrategieEnum.GRADUEL)
                .build();

        JoueurEntity joueur2 = JoueurEntity.builder()
                .nomJoueur("Joueur 2")
                .build();

        PartieEntity partie = PartieEntity.builder()
                .statut(StatutPartieEnum.EN_COURS)
                .ordre(1)
                .partiesJoueur(new ArrayList<>())
                .build();

        PartieJoueurEntity partieJoueur1 = PartieJoueurEntity.builder()
                .joueur(joueur1)
                .partie(partie)
                .score(0)
                .build();

        PartieJoueurEntity partieJoueur2 = PartieJoueurEntity.builder()
                .joueur(joueur2)
                .partie(partie)
                .score(0)
                .build();

        partie.getPartiesJoueur().add(partieJoueur1);
        partie.getPartiesJoueur().add(partieJoueur2);

        partieJoueur2.setCoup(CoupEnum.TRAHIR);

        GraduelStrategie strategie1 = new GraduelStrategie();

        List<PartieEntity> partieEntities = new ArrayList<>();
        partieEntities.add(partie);

        // Joueur 1 doit coopérer après la trahison
        CoupEnum coupJoueur1 = strategie1.getCoup(partieEntities);
        assertEquals(CoupEnum.COOPERER, coupJoueur1);

        partieJoueur2.setCoup(CoupEnum.TRAHIR);

        coupJoueur1 = strategie1.getCoup(partieEntities);
        assertEquals(CoupEnum.COOPERER, coupJoueur1);
    }

    @Test
    void testGraduelStrategieCooperationAfterCooperation() {
        // Créer les joueurs
        JoueurEntity joueur1 = JoueurEntity.builder()
                .nomJoueur("Joueur 1")
                .strategie(StrategieEnum.GRADUEL)
                .build();

        JoueurEntity joueur2 = JoueurEntity.builder()
                .nomJoueur("Joueur 2")
                .strategie(StrategieEnum.GRADUEL)
                .build();

        // Créer une partie
        PartieEntity partie = PartieEntity.builder()
                .statut(StatutPartieEnum.EN_COURS)
                .ordre(1)
                .partiesJoueur(new ArrayList<>())
                .build();

        PartieJoueurEntity partieJoueur1 = PartieJoueurEntity.builder()
                .joueur(joueur1)
                .partie(partie)
                .score(0)
                .build();

        PartieJoueurEntity partieJoueur2 = PartieJoueurEntity.builder()
                .joueur(joueur2)
                .partie(partie)
                .score(0)
                .build();

        partie.getPartiesJoueur().add(partieJoueur1);
        partie.getPartiesJoueur().add(partieJoueur2);

        partieJoueur2.setCoup(CoupEnum.COOPERER); // Joueur 2 coopère

        GraduelStrategie strategie1 = new GraduelStrategie();

        List<PartieEntity> partieEntities = new ArrayList<>();
        partieEntities.add(partie);

        CoupEnum coupJoueur1 = strategie1.getCoup(partieEntities);
        assertEquals(CoupEnum.COOPERER, coupJoueur1);

        partieJoueur2.setCoup(CoupEnum.COOPERER);

        coupJoueur1 = strategie1.getCoup(partieEntities);
        assertEquals(CoupEnum.COOPERER, coupJoueur1);
    }

}