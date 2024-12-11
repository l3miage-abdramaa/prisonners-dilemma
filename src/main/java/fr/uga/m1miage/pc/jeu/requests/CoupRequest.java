package fr.uga.m1miage.pc.jeu.requests;

import fr.uga.m1miage.pc.jeu.enums.CoupEnum;
import lombok.*;


@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CoupRequest {
    private CoupEnum coup;
}
