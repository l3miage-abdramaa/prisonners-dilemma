package fr.uga.m1miage.pc.infrastructure.adapter.repository;

import fr.uga.m1miage.pc.domain.model.PartieJoueurEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class PartieJoueurRepositoryAdapter implements fr.uga.m1miage.pc.domain.port.PartieJoueurRepositoryPort {

    private final PartieJoueurRepository partieJoueurRepository;
    @Override
    public Optional<PartieJoueurEntity> findById(UUID id) {
        return partieJoueurRepository.findById(id);
    }

    @Override
    public PartieJoueurEntity save(PartieJoueurEntity partieJoueur) {
        return partieJoueurRepository.save(partieJoueur);
    }

    @Override
    public List<PartieJoueurEntity> saveAll(List<PartieJoueurEntity> partieJoueurs) {
        return partieJoueurRepository.saveAll(partieJoueurs);
    }
}
