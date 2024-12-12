package fr.uga.m1miage.pc.jeu.models;

import fr.uga.m1miage.pc.jeu.enums.StatutPartieEnum;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "partie")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PartieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private StatutPartieEnum statut;

    @Column(nullable = true)
    private int ordre;

    @ManyToOne
    @JoinColumn(name = "jeu_id")
    private JeuEntity jeu;

    @OneToMany(mappedBy = "partie", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PartieJoueurEntity> partiesJoueur ;
}
