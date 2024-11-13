package fr.uga.m1miage.pc.joueur.strategies;

import fr.uga.m1miage.pc.partie.enums.CoupEnum;
import fr.uga.m1miage.pc.partie.models.PartieEntity;
import fr.uga.m1miage.pc.partie.models.PartieJoueurEntity;

import java.security.SecureRandom;
import java.util.List;

public class DonnantPourDeuxDonnantsAleatoireStrategie implements StrategieInterface{

    private CoupEnum dernierCoupAdverse ;
    private int compteurCoupIdentique;

    private final SecureRandom secureRandom ;

    public DonnantPourDeuxDonnantsAleatoireStrategie() {
        this.dernierCoupAdverse =  null;
        this.compteurCoupIdentique = 0;
        this.secureRandom = new SecureRandom();
    }


    @Override
    public CoupEnum getCoup(List<PartieEntity> parties) {

        // Vérification des coups précédents
        List<PartieJoueurEntity> partieJoueurEntities = parties.stream()
                .flatMap(partie -> partie.getPartiesJoueur().stream())
                .toList();

        // Vérification du dernier coup adverse
        if (!partieJoueurEntities.isEmpty()) {
            PartieJoueurEntity dernierPartieJoueur = partieJoueurEntities.get(partieJoueurEntities.size() - 1);
            CoupEnum coupAdverse = dernierPartieJoueur.getCoup();

            // Vérification si le coup adverse est identique au dernier coup
            if (coupAdverse != null) {
                if (coupAdverse.equals(dernierCoupAdverse)) {
                    compteurCoupIdentique++;
                }
                else {
                    compteurCoupIdentique = 1;
                }
                dernierCoupAdverse = coupAdverse;
            }

        }

        // Décision de jouer aléatoirement avec une probabilité
        if (secureRandom.nextDouble()<0.2){ // 20% de chance de jouer aléatoirement
            return secureRandom.nextBoolean() ? CoupEnum.COOPERER : CoupEnum.TRAHIR;
        }

        // Si l'adversaire a joué le même coup deux fois, réciprocité
        if(compteurCoupIdentique >= 2) {
            return dernierCoupAdverse;
        }

        //  Sinon, on joue COOPERER par défaut
        return CoupEnum.COOPERER;
    }
}
