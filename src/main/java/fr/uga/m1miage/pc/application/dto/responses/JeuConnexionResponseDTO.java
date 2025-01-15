package fr.uga.m1miage.pc.application.dto.responses;

import fr.uga.m1miage.pc.domain.enums.StatutJeuEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@AllArgsConstructor
public class JeuConnexionResponseDTO {
    private Long id;
    private StatutJeuEnum statut;
    private JoueurDTO joueurCree;
}
