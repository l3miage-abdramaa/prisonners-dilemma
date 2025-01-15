package fr.uga.m1miage.pc.application.dto.responses;

import fr.uga.m1miage.pc.domain.enums.StatutJeuEnum;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
public class JeuCreationResponseDTO {
    private Long id;
    private StatutJeuEnum statut;
    private int nombreParties;
    private JoueurDTO joueurCree;
}
