package fr.uga.m1miage.pc.jeu.response;

import fr.uga.m1miage.pc.jeu.enums.StatutPartieEnum;
import lombok.*;

import java.util.UUID;

@Getter
@Builder
public class PartieDTO {
    private UUID id;
    private StatutPartieEnum statut;
    private int ordre;
}
