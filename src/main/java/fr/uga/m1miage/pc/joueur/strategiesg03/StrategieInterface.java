package fr.uga.m1miage.pc.joueur.strategiesg03;

import fr.uga.m1miage.pc.partie.enums.CoupEnum;
import fr.uga.m1miage.pc.partie.models.PartieEntity;

import java.util.List;

public interface StrategieInterface {
    CoupEnum getCoup(List<PartieEntity> parties);
}
