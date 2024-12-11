package fr.uga.m1miage.pc.jeu.strategies;

import fr.uga.m1miage.pc.jeu.models.JoueurEntity;
import fr.uga.m1miage.pc.jeu.enums.CoupEnum;
import fr.uga.m1miage.pc.jeu.models.PartieEntity;
import fr.uga.m1miage.pc.jeu.models.PartieJoueurEntity;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RancunierDouxStrategieTest {

    @Test
    void testRancunierDouxStrategie() {JoueurEntity joueur1 = mock(JoueurEntity.class);
        JoueurEntity joueur2 = mock(JoueurEntity.class);
        PartieEntity partie = mock(PartieEntity.class);
        PartieJoueurEntity partieJoueur1 = mock(PartieJoueurEntity.class);
        PartieJoueurEntity partieJoueur2 = mock(PartieJoueurEntity.class);

        when(joueur1.getNomJoueur()).thenReturn("Joueur 1");
        when(joueur2.getNomJoueur()).thenReturn("Joueur 2");
        when(partieJoueur1.getJoueur()).thenReturn(joueur1);
        when(partieJoueur2.getJoueur()).thenReturn(joueur2);
        when(partie.getPartiesJoueur()).thenReturn(List.of(partieJoueur1, partieJoueur2));

        RancunierDouxStrategie strategie = new RancunierDouxStrategie();

        CoupEnum coup1 = strategie.getCoup(List.of(partie));
        assertEquals(CoupEnum.COOPERER, coup1);

        when(partieJoueur2.getCoup()).thenReturn(CoupEnum .TRAHIR);
        when(partieJoueur2.getCoup()).thenReturn(CoupEnum.COOPERER);

        CoupEnum coup3 = strategie.getCoup(List.of(partie));
        assertEquals(CoupEnum.COOPERER, coup3);
    }
}