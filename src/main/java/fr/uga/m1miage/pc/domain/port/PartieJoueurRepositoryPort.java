package fr.uga.m1miage.pc.domain.port;

import fr.uga.m1miage.pc.domain.model.PartieJoueurEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PartieJoueurRepositoryPort {

    Optional<PartieJoueurEntity> findById(UUID id);

    PartieJoueurEntity save(PartieJoueurEntity partieJoueur);

    List<PartieJoueurEntity> saveAll(List<PartieJoueurEntity> partieJoueurs);

}
