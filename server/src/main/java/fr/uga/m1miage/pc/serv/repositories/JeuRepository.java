package fr.uga.m1miage.pc.serv.repositories;



import fr.uga.m1miage.pc.serv.models.JeuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface JeuRepository extends JpaRepository<JeuEntity,Long> {
}
