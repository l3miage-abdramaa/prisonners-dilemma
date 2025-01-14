package fr.uga.m1miage.pc.restApi.requests;

import fr.uga.m1miage.pc.restApi.enums.StrategieEnum;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AbandonRequestDTO {
    private StrategieEnum strategie;
}
