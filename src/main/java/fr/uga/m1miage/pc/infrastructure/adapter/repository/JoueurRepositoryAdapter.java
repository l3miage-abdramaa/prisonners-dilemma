package fr.uga.m1miage.pc.infrastructure.adapter.repository;

import fr.uga.m1miage.pc.domain.model.JoueurEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class JoueurRepositoryAdapter implements fr.uga.m1miage.pc.domain.port.JoueurRepositoryPort {
    private final JoueurRepository joueurRepository;

    @Override
    public Optional<JoueurEntity> findById(UUID id) {
        return joueurRepository.findById(id);
    }

    @Override
    public JoueurEntity save(JoueurEntity joueur) {
        return joueurRepository.save(joueur);
    }

    @Override
    public List<JoueurEntity> findByJeuId(Long idJeu) {
        return joueurRepository.findByJeuId(idJeu);
    }
}
