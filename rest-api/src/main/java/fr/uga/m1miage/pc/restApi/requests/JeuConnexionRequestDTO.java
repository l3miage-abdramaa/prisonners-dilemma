package fr.uga.m1miage.pc.restApi.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class JeuConnexionRequestDTO {
    private String pseudoJoueur;
    private Long codeJeu;
}
