package fr.uga.m1miage.pc.Joueur.reponse;

import fr.uga.m1miage.pc.Joueur.enums.StrategieEnum;
import lombok.Data;

import java.util.UUID;

@Data
public class JoueurDTO {
    private UUID id;
    private String nom;
    private StrategieEnum strategie;
    private Boolean abandon;
}
