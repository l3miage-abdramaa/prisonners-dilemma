package fr.uga.m1miage.pc.serv.models;


import fr.uga.m1miage.pc.restApi.enums.StrategieEnum;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "joueur")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JoueurEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nomJoueur;

    private Boolean abandon ;

    @Enumerated(EnumType.STRING)
    private StrategieEnum strategie;

    @ManyToOne
    private JeuEntity jeu;

    @OneToMany(mappedBy = "joueur")
    private List<PartieJoueurEntity> partieJoueurs;

}
