package fr.uga.m1miage.pc.domain.strategies;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import fr.uga.m1miage.pc.domain.enums.CoupEnum;
import fr.uga.m1miage.pc.domain.model.PartieEntity;
import fr.uga.m1miage.pc.domain.model.PartieJoueurEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        assertEquals(CoupEnum.COOPERER, coup, "Le coup doit être COOPERER par défaut.");
    }

    @Test
    void testUnCoupPrecedent() {
        List<PartieEntity> parties = new ArrayList<>();
        PartieEntity partie = mock(PartieEntity.class);
        PartieJoueurEntity joueur = mock(PartieJoueurEntity.class);

        when(joueur.getCoup()).thenReturn(CoupEnum.COOPERER);
        when(partie.getPartiesJoueur()).thenReturn(List.of(joueur));
        parties.add(partie);

        CoupEnum coup = strategie.getCoup(parties);
        assertEquals(CoupEnum.COOPERER, coup, "Le coup doit être COOPERER par défaut.");
    }

    @Test
    void testDeuxCoupsIdentiques() {
        List<PartieEntity> parties = new ArrayList<>();
        PartieEntity partie1 = mock(PartieEntity.class);
        PartieEntity partie2 = mock(PartieEntity.class);
        PartieJoueurEntity joueur1 = mock(PartieJoueurEntity.class);
        PartieJoueurEntity joueur2 = mock(PartieJoueurEntity.class);

        when(joueur1.getCoup()).thenReturn(CoupEnum.COOPERER);
        when(joueur2.getCoup()).thenReturn(CoupEnum.COOPERER);
        when(partie1.getPartiesJoueur()).thenReturn(List.of(joueur1));
        when(partie2.getPartiesJoueur()).thenReturn(List.of(joueur2));
        parties.add(partie1);
        parties.add(partie2);

        strategie.getCoup(parties);
        CoupEnum coup = strategie.getCoup(parties);
        assertEquals(CoupEnum.COOPERER, coup, "Le coup doit être le même que le dernier coup adverse.");
    }


    @Test
    void testDeuxCoupsIdentiquesSuivisDunDifferent() {
        List<PartieEntity> parties = new ArrayList<>();
        PartieEntity partie1 = mock(PartieEntity.class);
        PartieEntity partie2 = mock(PartieEntity.class);
        PartieJoueurEntity joueur1 = mock(PartieJoueurEntity.class);
        PartieJoueurEntity joueur2 = mock(PartieJoueurEntity.class);

        when(joueur1.getCoup()).thenReturn(CoupEnum.COOPERER);
        when(joueur2.getCoup()).thenReturn(CoupEnum.COOPERER);
        when(partie1.getPartiesJoueur()).thenReturn(List.of(joueur1));
        when(partie2.getPartiesJoueur()).thenReturn(List.of(joueur2));
        parties.add(partie1);
        parties.add(partie2);

        strategie.getCoup(parties);
        strategie.getCoup(parties);
        when(joueur2.getCoup()).thenReturn(CoupEnum.TRAHIR);
        CoupEnum coup = strategie.getCoup(parties);
        assertEquals(CoupEnum.COOPERER, coup, "Le coup doit être le même que le dernier coup adverse.");
    }
}