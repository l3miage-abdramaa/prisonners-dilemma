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

        // V�rification des coups pr�c�dents
        List<PartieJoueurEntity> partieJoueurEntities = parties.stream()
                .flatMap(partie -> partie.getPartiesJoueur().stream())
                .toList();

        // V�rification du dernier coup adverse
        if (!partieJoueurEntities.isEmpty()) {
            PartieJoueurEntity dernierPartieJoueur = partieJoueurEntities.get(partieJoueurEntities.size() - 1);
            CoupEnum coupAdverse = dernierPartieJoueur.getCoup();

            // V�rification si le coup adverse est identique au dernier coup
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

        // D�cision de jouer al�atoirement avec une probabilit�
        if (secureRandom.nextDouble()<0.2){ // 20% de chance de jouer al�atoirement
            return secureRandom.nextBoolean() ? CoupEnum.COOPERER : CoupEnum.TRAHIR;
        }

        // Si l'adversaire a jou� le m�me coup deux fois, r�ciprocit�
        if(compteurCoupIdentique >= 2) {
            return dernierCoupAdverse;
        }

        //  Sinon, on joue COOPERER par d�faut
        return CoupEnum.COOPERER;
    }
}
