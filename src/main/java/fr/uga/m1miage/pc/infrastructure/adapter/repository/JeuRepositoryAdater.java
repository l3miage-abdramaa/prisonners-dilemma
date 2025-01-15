package fr.uga.m1miage.pc.infrastructure.adapter.repository;

import fr.uga.m1miage.pc.domain.model.JeuEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class JeuRepositoryAdater implements fr.uga.m1miage.pc.domain.port.JeuRepositoryPort {

    private final JeuRepository jeuRepository;
    @Override
    public Optional<JeuEntity> findById(Long id) {
        return jeuRepository.findById(id);
    }

    @Override
    public JeuEntity save(JeuEntity jeu) {
        return jeuRepository.save(jeu);
    }
}
