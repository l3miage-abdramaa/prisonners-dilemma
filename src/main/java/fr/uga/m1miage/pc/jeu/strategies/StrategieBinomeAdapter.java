package fr.uga.m1miage.pc.jeu.strategies;

import fr.uga.m1miage.pc.jeu.enums.CoupEnum;
import fr.uga.m1miage.pc.jeu.models.PartieEntity;
import fr.uga.miage.pc.g2_7.Choice;
import fr.uga.miage.pc.g2_7.StrategyBinome;

import java.security.SecureRandom;
import java.util.List;

public class StrategieBinomeAdapter implements StrategieInterface {

    private final StrategyBinome strategieBinome;
    private SecureRandom random = new SecureRandom();

    public StrategieBinomeAdapter(StrategyBinome strategieBinome) {
        this.strategieBinome = strategieBinome;
    }

    @Override
    public CoupEnum getCoup(List<PartieEntity> parties) {
        if (strategieBinome instanceof fr.uga.miage.pc.g2_7.ToujoursTrahir) {
            return CoupEnum.TRAHIR;
        }

        if (strategieBinome instanceof fr.uga.miage.pc.g2_7.Aleatoire) {
            return fiftyOrFifty();
        }

        Choice choix = strategieBinome.makeChoice(List.of(), List.of(), 0);
        return convertToCoupEnum(choix);
    }

    private CoupEnum fiftyOrFifty() {
        return random.nextDouble() > 0.5 ? CoupEnum.COOPERER : CoupEnum.TRAHIR;
    }

    private CoupEnum convertToCoupEnum(Choice choix) {
        return choix == Choice.COOPERER ? CoupEnum.COOPERER : CoupEnum.TRAHIR;
    }
}
