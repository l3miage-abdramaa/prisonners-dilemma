package fr.uga.m1miage.pc.serv.strategies;

import fr.uga.m1miage.pc.restApi.enums.CoupEnum;
import fr.uga.miage.pc.g2_7.Choice;
import fr.uga.miage.pc.g2_7.StrategyBinome;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;



import java.util.List;

class StrategieBinomeAdapterTest {

    @Test
    void testToujoursTrahir() {
        StrategyBinome toujoursTrahir = new fr.uga.miage.pc.g2_7.ToujoursTrahir();
        StrategieBinomeAdapter adapter = new StrategieBinomeAdapter(toujoursTrahir);
        CoupEnum coup = adapter.getCoup(List.of());
        Assertions.assertEquals(CoupEnum.TRAHIR, coup, "La stratégie ToujoursTrahir doit toujours trahir.");
    }

    @Test
    void testAleatoire() {
        StrategyBinome aleatoire = new fr.uga.miage.pc.g2_7.Aleatoire();
        StrategieBinomeAdapter adapter = new StrategieBinomeAdapter(aleatoire);
        CoupEnum coup = adapter.getCoup(List.of());
        Assertions.assertTrue(coup == CoupEnum.COOPERER || coup == CoupEnum.TRAHIR, "La stratégie Aleatoire doit renvoyer COOPERER ou TRAHIR.");
    }

    @Test
    void testDefaultStrategy() {
        StrategyBinome customStrategy = new StrategyBinome() {
            @Override
            public Choice makeChoice(List<Choice> pastActionsPlayer, List<Choice> pastActionsOtherPlayer, int tour) {
                return Choice.COOPERER;
            }
        };
        StrategieBinomeAdapter adapter = new StrategieBinomeAdapter(customStrategy);
        CoupEnum coup = adapter.getCoup(List.of());
        Assertions.assertEquals(CoupEnum.COOPERER, coup, "La stratégie personnalisée doit renvoyer COOPERER.");
    }

    @Test
    void testDefaultStrategyTrahir() {
        StrategyBinome customStrategy = new StrategyBinome() {
            @Override
            public Choice makeChoice(List<Choice> pastActionsPlayer, List<Choice> pastActionsOtherPlayer, int tour) {
                return Choice.TRAHIR;
            }
        };
        StrategieBinomeAdapter adapter = new StrategieBinomeAdapter(customStrategy);
        CoupEnum coup = adapter.getCoup(List.of());
        Assertions.assertEquals(CoupEnum.TRAHIR, coup, "La stratégie personnalisée doit renvoyer TRAHIR.");
    }
}
