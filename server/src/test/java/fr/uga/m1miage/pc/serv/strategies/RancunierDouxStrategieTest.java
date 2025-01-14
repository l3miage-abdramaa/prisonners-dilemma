package fr.uga.m1miage.pc.serv.strategies;


import fr.uga.m1miage.pc.restApi.enums.CoupEnum;
import fr.uga.m1miage.pc.serv.models.JoueurEntity;
import fr.uga.m1miage.pc.serv.models.PartieEntity;
import fr.uga.m1miage.pc.serv.models.PartieJoueurEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

class RancunierDouxStrategieTest {

    @Test
    void testRancunierDouxStrategie() {
        JoueurEntity joueur1 = Mockito.mock(JoueurEntity.class);
        JoueurEntity joueur2 = Mockito.mock(JoueurEntity.class);
        PartieEntity partie = Mockito.mock(PartieEntity.class);
        PartieJoueurEntity partieJoueur1 = Mockito.mock(PartieJoueurEntity.class);
        PartieJoueurEntity partieJoueur2 = Mockito.mock(PartieJoueurEntity.class);

        Mockito.when(joueur1.getNomJoueur()).thenReturn("Joueur 1");
        Mockito.when(joueur2.getNomJoueur()).thenReturn("Joueur 2");
        Mockito.when(partieJoueur1.getJoueur()).thenReturn(joueur1);
        Mockito.when(partieJoueur2.getJoueur()).thenReturn(joueur2);
        Mockito.when(partie.getPartiesJoueur()).thenReturn(List.of(partieJoueur1, partieJoueur2));

        RancunierDouxStrategie strategie = new RancunierDouxStrategie();

        CoupEnum coup1 = strategie.getCoup(List.of(partie));
        Assertions.assertEquals(CoupEnum.COOPERER, coup1);

        Mockito.when(partieJoueur2.getCoup()).thenReturn(CoupEnum .TRAHIR);
        Mockito.when(partieJoueur2.getCoup()).thenReturn(CoupEnum.COOPERER);

        CoupEnum coup3 = strategie.getCoup(List.of(partie));
        Assertions.assertEquals(CoupEnum.COOPERER, coup3);
    }
}