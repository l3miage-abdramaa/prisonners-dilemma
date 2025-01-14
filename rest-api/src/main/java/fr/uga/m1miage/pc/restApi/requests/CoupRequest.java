package fr.uga.m1miage.pc.restApi.requests;

import fr.uga.m1miage.pc.restApi.enums.CoupEnum;
import lombok.*;


@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CoupRequest {
    private CoupEnum coup;
}
