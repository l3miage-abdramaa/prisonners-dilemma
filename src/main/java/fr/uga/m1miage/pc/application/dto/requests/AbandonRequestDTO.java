package fr.uga.m1miage.pc.application.dto.requests;

import fr.uga.m1miage.pc.domain.enums.StrategieEnum;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AbandonRequestDTO {
    private StrategieEnum strategie;
}
