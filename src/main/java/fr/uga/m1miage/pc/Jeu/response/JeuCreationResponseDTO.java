package fr.uga.m1miage.pc.Jeu.response;

import fr.uga.m1miage.pc.Jeu.enums.StatutEnum;
import fr.uga.m1miage.pc.Joueur.reponse.JoueurDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JeuCreationResponseDTO {
    private Long id;
    private StatutEnum statut;
    private int nombreParties;
}