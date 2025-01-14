package fr.uga.m1miage.pc.serv.models;


import fr.uga.m1miage.pc.restApi.enums.StatutJeuEnum;
import jakarta.persistence.*;
import lombok.*;

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
