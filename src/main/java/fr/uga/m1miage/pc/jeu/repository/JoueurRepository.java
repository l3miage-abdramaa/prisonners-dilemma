package fr.uga.m1miage.pc.jeu.repository;

import fr.uga.m1miage.pc.jeu.models.JoueurEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JoueurRepository extends JpaRepository<JoueurEntity, UUID> {
    List<JoueurEntity> findByJeuId(Long idJeu);
}
