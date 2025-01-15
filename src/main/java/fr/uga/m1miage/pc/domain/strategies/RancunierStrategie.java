package fr.uga.m1miage.pc.domain.strategies;

import fr.uga.m1miage.pc.domain.enums.CoupEnum;
import fr.uga.m1miage.pc.domain.model.PartieEntity;
import fr.uga.m1miage.pc.domain.model.PartieJoueurEntity;

import java.util.List;

public class RancunierStrategie implements StrategieInterface {

    @Override
    public CoupEnum getCoup(List<PartieEntity> parties) {
        List<PartieJoueurEntity> partieJoueurEntities = parties.stream()
                .flatMap(partie -> partie.getPartiesJoueur().stream()).toList();

        PartieJoueurEntity partieJoueurCoupTrahir = partieJoueurEntities.stream()
                .filter(partieJoueurEntity -> partieJoueurEntity.getJoueur().getAbandon() == null)
                .filter(partieJoueurEntity -> partieJoueurEntity.getCoup().equals(CoupEnum.TRAHIR)).findAny()
                .orElse(null);

        return partieJoueurCoupTrahir != null ? CoupEnum.TRAHIR : CoupEnum.COOPERER;

    }
}
