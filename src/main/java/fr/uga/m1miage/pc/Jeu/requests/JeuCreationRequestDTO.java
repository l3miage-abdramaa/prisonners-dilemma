package fr.uga.m1miage.pc.Jeu.requests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JeuCreationRequestDTO {
    private String pseudoJoueur;
    private int nombreParties;
}