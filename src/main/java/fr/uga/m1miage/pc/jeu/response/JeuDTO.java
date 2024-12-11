package fr.uga.m1miage.pc.jeu.response;

import fr.uga.m1miage.pc.jeu.enums.StatutJeuEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class JeuDTO {
    private Long id;
    private int nombreParties;
    private StatutJeuEnum statut;
    private List<PartieDTO> parties;
    private List<JoueurDTO> joueurs;
}
