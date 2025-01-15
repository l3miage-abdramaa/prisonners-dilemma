package fr.uga.m1miage.pc.domain.model;


import fr.uga.m1miage.pc.domain.enums.CoupEnum;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@Table(name = "partie_joueur")
@NoArgsConstructor
@AllArgsConstructor
public class PartieJoueurEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


    private int score;

    @Enumerated(EnumType.STRING)
    private CoupEnum coup;

    @ManyToOne
    private JoueurEntity joueur;

    @ManyToOne
    private PartieEntity partie;


}
