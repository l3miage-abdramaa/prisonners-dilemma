package fr.uga.m1miage.pc.jeu.strategies;

import fr.uga.m1miage.pc.jeu.enums.CoupEnum;
import fr.uga.m1miage.pc.jeu.models.PartieEntity;

import java.util.List;

public class ToujoursCoopererStrategie implements StrategieInterface{

    @Override
    public CoupEnum getCoup(List<PartieEntity> parties) {
        return CoupEnum.COOPERER;
    }
}
