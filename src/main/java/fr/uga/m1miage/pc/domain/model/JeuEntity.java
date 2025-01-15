package fr.uga.m1miage.pc.domain.model;


import fr.uga.m1miage.pc.domain.enums.StatutJeuEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "jeu")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JeuEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int nombreParties;

    @Enumerated(EnumType.STRING)
    private StatutJeuEnum statut;

    @OneToMany(mappedBy = "jeu")
    private List<PartieEntity> parties;

    @OneToMany(mappedBy = "jeu")
    private List<JoueurEntity> joueurs;

    @Transient
    private JoueurEntity joueurCree;

}
