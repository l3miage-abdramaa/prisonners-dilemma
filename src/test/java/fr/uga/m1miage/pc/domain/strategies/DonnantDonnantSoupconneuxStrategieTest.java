package fr.uga.m1miage.pc.domain.strategies;

import fr.uga.m1miage.pc.domain.enums.CoupEnum;
import fr.uga.m1miage.pc.domain.model.JoueurEntity;
import fr.uga.m1miage.pc.domain.model.PartieEntity;
import fr.uga.m1miage.pc.domain.model.PartieJoueurEntity;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DonnantDonnantSoupconneuxStrategieTest {

    @Test
    void testDonnantDonnantSoupconneuxStrategie() {
        JoueurEntity joueur1 = Mockito.mock(JoueurEntity.class);
        JoueurEntity joueur2 = Mockito.mock(JoueurEntity.class);
        PartieEntity partie = Mockito.mock(PartieEntity.class);
        PartieJoueurEntity partieJoueur1 = Mockito.mock(PartieJoueurEntity.class);
        PartieJoueurEntity partieJoueur2 = Mockito.mock(PartieJoueurEntity.class);

        Mockito.when(joueur1.getNomJoueur()).thenReturn("Joueur 1");
        Mockito.when(joueur2.getNomJoueur()).thenReturn("Joueur 2");
        Mockito.when(partieJoueur1.getJoueur()).thenReturn(joueur1);
        Mockito.when(partieJoueur2.getJoueur()).thenReturn(joueur2);
        Mockito.when(partieJoueur2.getCoup()).thenReturn(CoupEnum.COOPERER);
        Mockito.when(partie.getPartiesJoueur()).thenReturn(List.of(partieJoueur1, partieJoueur2));

        DonnantDonnantSoupconneuxStrategie strategie1 = new DonnantDonnantSoupconneuxStrategie();

        List<PartieEntity> partieEntities = new ArrayList<>();
        partieEntities.add(partie);
        CoupEnum coupJoueur1 = strategie1.getCoup(partieEntities);
        assertEquals(CoupEnum.TRAHIR, coupJoueur1);

        Mockito.when(partieJoueur2.getCoup()).thenReturn(CoupEnum.COOPERER);

        CoupEnum coupJoueur1Suivant = strategie1.getCoup(partieEntities);
        assertEquals(CoupEnum.COOPERER, coupJoueur1Suivant);
    }
}