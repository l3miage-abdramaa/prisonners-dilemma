package fr.uga.m1miage.pc.application.dto.responses;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PartieJoueurDTO {
    private String coup;
    private int score;
    private JoueurDTO joueur;
    private PartieDTO partie;
}
