package fr.uga.m1miage.pc.application.dto.responses;

import fr.uga.m1miage.pc.domain.enums.StrategieEnum;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class JoueurDTO {
    private UUID id;
    private String nom;
    private StrategieEnum strategie;
    private Boolean abandon;
}
