package fr.uga.m1miage.pc.application.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
@AllArgsConstructor
public class JeuCreationRequestDTO {
    private String pseudoJoueur;
    private int nombreParties;
}
