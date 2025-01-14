package fr.uga.m1miage.pc.serv.strategies;



import fr.uga.m1miage.pc.restApi.enums.CoupEnum;
import fr.uga.m1miage.pc.restApi.enums.StrategieEnum;
import fr.uga.m1miage.pc.serv.models.PartieEntity;

import java.util.List;

public class Strategie {
    public CoupEnum getCoup(List<PartieEntity> parties, StrategieEnum strategieEnum) {
        StrategieInterface strategieImpl = StrategieFactory.getStrategie(strategieEnum);
        return strategieImpl.getCoup(parties);
    }
}
