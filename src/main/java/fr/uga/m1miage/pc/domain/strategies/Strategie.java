package fr.uga.m1miage.pc.domain.strategies;

import fr.uga.m1miage.pc.domain.enums.StrategieEnum;
import fr.uga.m1miage.pc.domain.enums.CoupEnum;
import fr.uga.m1miage.pc.domain.model.PartieEntity;

import java.util.List;

public class Strategie {
    public CoupEnum getCoup(List<PartieEntity> parties, StrategieEnum strategieEnum) {
        StrategieInterface strategieImpl = StrategieFactory.getStrategie(strategieEnum);
        return strategieImpl.getCoup(parties);
    }
}
