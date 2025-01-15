package fr.uga.m1miage.pc.domain.port;

import fr.uga.m1miage.pc.domain.enums.StatutPartieEnum;
import fr.uga.m1miage.pc.domain.model.PartieEntity;

import java.util.Optional;
import java.util.UUID;

public interface PartieRepositoryPort {
    Optional<PartieEntity> findById(UUID idPartie);

    PartieEntity findByJeuIdAndStatut(Long idJeu, StatutPartieEnum statut);

    PartieEntity save(PartieEntity partie);

}
