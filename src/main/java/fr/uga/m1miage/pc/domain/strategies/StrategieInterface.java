package fr.uga.m1miage.pc.domain.strategies;

import fr.uga.m1miage.pc.domain.enums.CoupEnum;
import fr.uga.m1miage.pc.domain.model.PartieEntity;

import java.util.List;

public interface StrategieInterface {
    CoupEnum getCoup(List<PartieEntity> parties);
}
