package fr.uga.m1miage.pc.application.dto.responses;


import fr.uga.m1miage.pc.domain.enums.StatutPartieEnum;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
public class PartieDetailsDTO {
    private UUID id;
    private StatutPartieEnum statut;
    private int ordre;
    List<PartieJoueurDTO> partiesJoueurs;
}
