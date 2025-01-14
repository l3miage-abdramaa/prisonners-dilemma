package fr.uga.m1miage.pc.serv.repositories;


import fr.uga.m1miage.pc.serv.models.PartieJoueurEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PartieJoueurRepository extends JpaRepository<PartieJoueurEntity, UUID> {
}
