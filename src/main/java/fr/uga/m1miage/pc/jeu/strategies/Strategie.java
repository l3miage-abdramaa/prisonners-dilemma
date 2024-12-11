package fr.uga.m1miage.pc.jeu.strategies;

import fr.uga.m1miage.pc.jeu.enums.StrategieEnum;
import fr.uga.m1miage.pc.jeu.enums.CoupEnum;
import fr.uga.m1miage.pc.jeu.models.PartieEntity;

import java.util.List;

public class Strategie {
    public CoupEnum getCoup(List<PartieEntity> parties, StrategieEnum strategieEnum) {
        StrategieInterface strategieImpl = StrategieFactory.getStrategie(strategieEnum);
        return strategieImpl.getCoup(parties);
    }
}
