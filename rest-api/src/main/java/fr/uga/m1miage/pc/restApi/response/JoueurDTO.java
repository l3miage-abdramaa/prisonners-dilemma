package fr.uga.m1miage.pc.restApi.response;

import fr.uga.m1miage.pc.restApi.enums.StrategieEnum;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class JoueurDTO {
    private UUID id;
    private String nomJoueur;
    private StrategieEnum strategie;
    private Boolean abandon;
}
