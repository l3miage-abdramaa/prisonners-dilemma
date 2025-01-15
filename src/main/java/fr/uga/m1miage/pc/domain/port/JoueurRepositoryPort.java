package fr.uga.m1miage.pc.domain.port;

import fr.uga.m1miage.pc.domain.model.JoueurEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface JoueurRepositoryPort {
    Optional<JoueurEntity> findById(UUID id);
    JoueurEntity save(JoueurEntity joueur);

    List<JoueurEntity> findByJeuId(Long idJeu);
}
