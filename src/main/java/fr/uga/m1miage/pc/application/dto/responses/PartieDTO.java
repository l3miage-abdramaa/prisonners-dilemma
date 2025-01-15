package fr.uga.m1miage.pc.application.dto.responses;

import fr.uga.m1miage.pc.domain.enums.StatutPartieEnum;
import lombok.*;

import java.util.UUID;

@Getter
@Builder
public class PartieDTO {
    private UUID id;
    private StatutPartieEnum statut;
    private int ordre;
}
