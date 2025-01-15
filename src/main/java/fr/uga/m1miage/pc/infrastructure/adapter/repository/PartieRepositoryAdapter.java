package fr.uga.m1miage.pc.infrastructure.adapter.repository;

import fr.uga.m1miage.pc.domain.enums.StatutPartieEnum;
import fr.uga.m1miage.pc.domain.model.PartieEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class PartieRepositoryAdapter implements fr.uga.m1miage.pc.domain.port.PartieRepositoryPort {
    private final PartieRepository partieRepository;
    @Override
    public Optional<PartieEntity> findById(UUID idPartie) {
        return partieRepository.findById(idPartie);
    }

    @Override
    public PartieEntity findByJeuIdAndStatut(Long idJeu, StatutPartieEnum statut) {
        return partieRepository.findByJeuIdAndStatut(idJeu,statut);
    }

    @Override
    public PartieEntity save(PartieEntity partie) {
        return partieRepository.save(partie);
    }
}
