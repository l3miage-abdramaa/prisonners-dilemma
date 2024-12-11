package fr.uga.m1miage.pc.joueur.strategies;

import fr.uga.m1miage.pc.partie.enums.CoupEnum;
import fr.uga.m1miage.pc.partie.models.PartieEntity;
import fr.uga.m1miage.pc.partie.models.PartieJoueurEntity;
import fr.uga.miage.pc.g2_7.Choice;
import fr.uga.miage.pc.g2_7.StrategyBinome;

import java.util.List;
import java.util.stream.Collectors;

public class StrategieBinomeAdapter implements StrategieInterface{

    private final StrategyBinome strategieBinome;

    public StrategieBinomeAdapter(StrategyBinome strategyBinome) {
        this.strategieBinome = strategyBinome;
    }

    @Override
    public CoupEnum getCoup(List<PartieEntity> parties) {

        List<Choice> actionsJoueur = parties.stream()
                .map(partie -> getCoupJoueur(partie, true))
                .map(this::convertToChoice)
                .collect(Collectors.toList());

        List<Choice> actionsAdversaire = parties.stream()
                .map(partie -> getCoupJoueur(partie, false))
                .map(this::convertToChoice)
                .collect(Collectors.toList());

        Choice choix = strategieBinome.makeChoice(actionsJoueur, actionsAdversaire, parties.size());

        return convertToCoupEnum(choix);
    }

    private CoupEnum getCoupJoueur(PartieEntity partie, boolean isCurrentPlayer) {
        PartieJoueurEntity partieJoueur = isCurrentPlayer
                ? partie.getPartiesJoueur().get(0)
                : partie.getPartiesJoueur().get(1);
        return partieJoueur.getCoup();
    }

    private Choice convertToChoice(CoupEnum coup) {
        return coup == CoupEnum.COOPERER ? Choice.COOPERER : Choice.TRAHIR;
    }

    private CoupEnum convertToCoupEnum(Choice choix) {
        return choix == Choice.COOPERER ? CoupEnum.COOPERER : CoupEnum.TRAHIR;
    }





}
