package fr.uga.m1miage.pc.serv.repositories;


import fr.uga.m1miage.pc.restApi.enums.StrategieEnum;
import fr.uga.m1miage.pc.serv.models.JeuEntity;
import fr.uga.m1miage.pc.serv.models.JoueurEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class JoueurRepositoryTest {

    @Mock
    private JoueurRepository joueurRepository ;


    @Test
    void testFindByJeuId() {

        JeuEntity jeu = new JeuEntity();
        jeu.setId(1L);

        JoueurEntity joueur1 = JoueurEntity
                .builder()
                .nomJoueur("joueur1")
                .jeu(jeu)
                .build();

        JoueurEntity joueur2 = JoueurEntity
                .builder()
                .nomJoueur("joueur2")
                .jeu(jeu)
                .build();
        Mockito.when(joueurRepository.findByJeuId(1L)).thenReturn(List.of(joueur1,joueur2));

        List<JoueurEntity> joueurs = joueurRepository.findByJeuId(1L);

        Assertions.assertEquals(2, joueurs.size());
        Assertions.assertEquals("joueur1",joueurs.get(0).getNomJoueur());
        Assertions.assertEquals("joueur2",joueurs.get(1).getNomJoueur());

        Mockito.verify(joueurRepository, Mockito.times(1)).findByJeuId(1L);

    }



    @Test
    void testAbandonnerJeu() {

        UUID idJoueur = UUID.randomUUID();
        JoueurEntity joueur = JoueurEntity.builder()
                .id(idJoueur)
                .nomJoueur("Joueur1")
                .strategie(StrategieEnum.DONNANT_DONNANT)
                .build();

        Mockito.when(joueurRepository.save(joueur)).thenReturn(joueur);
        Mockito.when(joueurRepository.findById(idJoueur)).thenReturn(Optional.of(joueur));

        joueur.setStrategie(StrategieEnum.TOUJOURS_TRAHIR);
        JoueurEntity updatedJoueur = joueurRepository.save(joueur);


        Assertions.assertNotNull(updatedJoueur);
        Assertions.assertEquals(StrategieEnum.TOUJOURS_TRAHIR, updatedJoueur.getStrategie());


        Mockito.verify(joueurRepository, Mockito.times(1)).save(joueur);
    }
}
