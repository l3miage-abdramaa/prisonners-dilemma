package fr.uga.m1miage.pc.jeu.strategies;

import fr.uga.m1miage.pc.jeu.enums.CoupEnum;
import fr.uga.m1miage.pc.jeu.models.PartieEntity;

import java.util.List;

public interface StrategieInterface {
    CoupEnum getCoup(List<PartieEntity> parties);
}
