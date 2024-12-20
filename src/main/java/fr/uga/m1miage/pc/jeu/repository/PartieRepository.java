package fr.uga.m1miage.pc.jeu.repository;



import fr.uga.m1miage.pc.jeu.enums.StatutPartieEnum;
import fr.uga.m1miage.pc.jeu.models.PartieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PartieRepository extends JpaRepository<PartieEntity, UUID> {

    PartieEntity findByJeuIdAndStatut(Long idJeu,StatutPartieEnum statut);
}
