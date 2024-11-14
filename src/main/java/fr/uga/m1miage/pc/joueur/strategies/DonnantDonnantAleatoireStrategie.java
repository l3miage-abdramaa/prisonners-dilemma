package fr.uga.m1miage.pc.joueur.strategies;

import fr.uga.m1miage.pc.partie.enums.CoupEnum;
import fr.uga.m1miage.pc.partie.models.PartieEntity;
import fr.uga.m1miage.pc.partie.models.PartieJoueurEntity;

import java.security.SecureRandom;
import java.util.List;

public class DonnantDonnantAleatoireStrategie implements StrategieInterface{

    private static final SecureRandom random = new SecureRandom();
    private static final double RANDOM_CHANCE = 0.2 ;

    @Override
    public CoupEnum getCoup(List<PartieEntity> parties) {


        List<PartieJoueurEntity> partieJoueurEntities = parties.stream()
                .flatMap(partie -> partie.getPartiesJoueur().stream())
                .toList();


        PartieJoueurEntity adversaireDernierCoup = partieJoueurEntities.stream()
                .filter(partieJoueurEntity -> partieJoueurEntity.getCoup() != null)
                .findFirst()
                .orElse(null);


        if(adversaireDernierCoup == null || random.nextDouble() < RANDOM_CHANCE) {
            return CoupEnum.values()[random.nextInt(CoupEnum.values().length)];
        }

        return adversaireDernierCoup.getCoup();

    }
}
