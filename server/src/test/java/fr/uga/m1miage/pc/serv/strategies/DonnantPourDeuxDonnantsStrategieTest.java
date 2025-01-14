package fr.uga.m1miage.pc.serv.strategies;


import fr.uga.m1miage.pc.restApi.enums.CoupEnum;
import fr.uga.m1miage.pc.serv.models.PartieEntity;
import fr.uga.m1miage.pc.serv.models.PartieJoueurEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

class DonnantPourDeuxDonnantsStrategieTest {

    private DonnantPourDeuxDonnantsStrategie strategie;

    @BeforeEach
    public void setUp() {
        strategie = new DonnantPourDeuxDonnantsStrategie();
    }

    @Test
    void testAucunCoupPrecedent() {
        List<PartieEntity> parties = new ArrayList<>();
        CoupEnum coup = strategie.getCoup(parties);
        Assertions.assertEquals(CoupEnum.COOPERER, coup, "Le coup doit être COOPERER par défaut.");
    }

    @Test
    void testUnCoupPrecedent() {
        List<PartieEntity> parties = new ArrayList<>();
        PartieEntity partie = Mockito.mock(PartieEntity.class);
        PartieJoueurEntity joueur = Mockito.mock(PartieJoueurEntity.class);

        Mockito.when(joueur.getCoup()).thenReturn(CoupEnum.COOPERER);
        Mockito.when(partie.getPartiesJoueur()).thenReturn(List.of(joueur));
        parties.add(partie);

        CoupEnum coup = strategie.getCoup(parties);
        Assertions.assertEquals(CoupEnum.COOPERER, coup, "Le coup doit être COOPERER par défaut.");
    }

    @Test
    void testDeuxCoupsIdentiques() {
        List<PartieEntity> parties = new ArrayList<>();
        PartieEntity partie1 = Mockito.mock(PartieEntity.class);
        PartieEntity partie2 = Mockito.mock(PartieEntity.class);
        PartieJoueurEntity joueur1 = Mockito.mock(PartieJoueurEntity.class);
        PartieJoueurEntity joueur2 = Mockito.mock(PartieJoueurEntity.class);

        Mockito.when(joueur1.getCoup()).thenReturn(CoupEnum.COOPERER);
        Mockito.when(joueur2.getCoup()).thenReturn(CoupEnum.COOPERER);
        Mockito.when(partie1.getPartiesJoueur()).thenReturn(List.of(joueur1));
        Mockito.when(partie2.getPartiesJoueur()).thenReturn(List.of(joueur2));
        parties.add(partie1);
        parties.add(partie2);

        strategie.getCoup(parties);
        CoupEnum coup = strategie.getCoup(parties);
        Assertions.assertEquals(CoupEnum.COOPERER, coup, "Le coup doit être le même que le dernier coup adverse.");
    }


    @Test
    void testDeuxCoupsIdentiquesSuivisDunDifferent() {
        List<PartieEntity> parties = new ArrayList<>();
        PartieEntity partie1 = Mockito.mock(PartieEntity.class);
        PartieEntity partie2 = Mockito.mock(PartieEntity.class);
        PartieJoueurEntity joueur1 = Mockito.mock(PartieJoueurEntity.class);
        PartieJoueurEntity joueur2 = Mockito.mock(PartieJoueurEntity.class);

        Mockito.when(joueur1.getCoup()).thenReturn(CoupEnum.COOPERER);
        Mockito.when(joueur2.getCoup()).thenReturn(CoupEnum.COOPERER);
        Mockito.when(partie1.getPartiesJoueur()).thenReturn(List.of(joueur1));
        Mockito.when(partie2.getPartiesJoueur()).thenReturn(List.of(joueur2));
        parties.add(partie1);
        parties.add(partie2);

        strategie.getCoup(parties);
        strategie.getCoup(parties);
        Mockito.when(joueur2.getCoup()).thenReturn(CoupEnum.TRAHIR);
        CoupEnum coup = strategie.getCoup(parties);
        Assertions.assertEquals(CoupEnum.COOPERER, coup, "Le coup doit être le même que le dernier coup adverse.");
    }
}