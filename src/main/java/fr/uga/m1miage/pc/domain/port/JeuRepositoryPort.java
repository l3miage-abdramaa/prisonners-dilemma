package fr.uga.m1miage.pc.domain.port;

import fr.uga.m1miage.pc.domain.model.JeuEntity;

import java.util.Optional;

public interface JeuRepositoryPort {
    Optional<JeuEntity> findById(Long id);

    JeuEntity save(JeuEntity jeu);
}
