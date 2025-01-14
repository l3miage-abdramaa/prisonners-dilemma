package fr.uga.m1miage.pc.restApi.response;


import fr.uga.m1miage.pc.restApi.enums.StatutPartieEnum;
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
    List<PartieJoueurDTO> partiesJoueur;
}
