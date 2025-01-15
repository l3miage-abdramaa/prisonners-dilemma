package fr.uga.m1miage.pc.infrastructure.adapter.repository;

import fr.uga.m1miage.pc.domain.model.JeuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface JeuRepository extends JpaRepository<JeuEntity,Long> {
}
