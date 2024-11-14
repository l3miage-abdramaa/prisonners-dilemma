package fr.uga.m1miage.pc.joueur.strategies;

import fr.uga.m1miage.pc.joueur.models.JoueurEntity;
import fr.uga.m1miage.pc.partie.enums.CoupEnum;
import fr.uga.m1miage.pc.partie.models.PartieEntity;
import fr.uga.m1miage.pc.partie.models.PartieJoueurEntity;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RancunierDouxStrategieTest {

    @Test
    void testRancunierDouxStrategie() {
        // Créer les mocks
        JoueurEntity joueur1 = mock(JoueurEntity.class);
        JoueurEntity joueur2 = mock(JoueurEntity.class);
        PartieEntity partie = mock(PartieEntity.class);
        PartieJoueurEntity partieJoueur1 = mock(PartieJoueurEntity.class);
        PartieJoueurEntity partieJoueur2 = mock(PartieJoueurEntity.class);

        // Configurer les mocks
        when(joueur1.getNomJoueur()).thenReturn("Joueur 1");
        when(joueur2.getNomJoueur()).thenReturn("Joueur 2");
        when(partieJoueur1.getJoueur()).thenReturn(joueur1);
        when(partieJoueur2.getJoueur()).thenReturn(joueur2);
        when(partie.getPartiesJoueur()).thenReturn(List.of(partieJoueur1, partieJoueur2));

        // Créer une instance de `RancunierDouxStrategie`
        RancunierDouxStrategie strategie = new RancunierDouxStrategie();

        // Premier coup, l'adversaire n'a pas encore joué
        CoupEnum coup1 = strategie.getCoup(List.of(partie));
        assertEquals(CoupEnum.COOPERER, coup1);

        // Simuler une trahison de l'adversaire
        when(partieJoueur2.getCoup()).thenReturn(CoupEnum .TRAHIR);



        // Simuler que l'adversaire coopère maintenant
        when(partieJoueur2.getCoup()).thenReturn(CoupEnum.COOPERER);

        // Troisième coup, l'adversaire coopère
        CoupEnum coup3 = strategie.getCoup(List.of(partie));
        assertEquals(CoupEnum.COOPERER, coup3);
    }
}