package fr.uga.m1miage.pc.serv.strategies;




import fr.uga.m1miage.pc.restApi.enums.CoupEnum;
import fr.uga.m1miage.pc.serv.models.PartieEntity;

import java.util.List;

public class ToujoursCoopererStrategie implements StrategieInterface{

    @Override
    public CoupEnum getCoup(List<PartieEntity> parties) {
        return CoupEnum.COOPERER;
    }
}
