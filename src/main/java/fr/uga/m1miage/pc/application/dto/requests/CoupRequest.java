package fr.uga.m1miage.pc.application.dto.requests;

import fr.uga.m1miage.pc.domain.enums.CoupEnum;
import lombok.*;


@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CoupRequest {
    private CoupEnum coup;
}
