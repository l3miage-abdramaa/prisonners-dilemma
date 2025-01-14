package fr.uga.m1miage.pc.serv.strategies;




import fr.uga.m1miage.pc.restApi.enums.CoupEnum;
import fr.uga.m1miage.pc.serv.models.PartieEntity;

import java.util.List;

public interface StrategieInterface {
    CoupEnum getCoup(List<PartieEntity> parties);
}
