package fr.uga.m1miage.pc.jeu.strategies;

import fr.uga.m1miage.pc.jeu.enums.CoupEnum;
import fr.uga.m1miage.pc.jeu.models.PartieEntity;
import fr.uga.m1miage.pc.jeu.models.PartieJoueurEntity;

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
