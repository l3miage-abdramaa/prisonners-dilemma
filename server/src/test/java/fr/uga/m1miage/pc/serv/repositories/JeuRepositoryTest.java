package fr.uga.m1miage.pc.serv.repositories;


import fr.uga.m1miage.pc.restApi.enums.StatutJeuEnum;
import fr.uga.m1miage.pc.serv.models.JeuEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;


import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class JeuRepositoryTest {

    @Mock
    private JeuRepository jeuRepository;


    @Test
    void testSaveJeu() {
        JeuEntity jeu = JeuEntity
                .builder()
                .nombreParties(3)
                .statut(StatutJeuEnum.EN_ATTENTE)
                .build();


        Mockito.when(jeuRepository.save(jeu)).thenReturn(jeu);

        JeuEntity savedJeu = jeuRepository.save(jeu);

        Assertions.assertNotNull(savedJeu);
        Assertions.assertEquals(3,savedJeu.getNombreParties());
        Assertions.assertEquals(StatutJeuEnum.EN_ATTENTE,savedJeu.getStatut());

        Mockito.verify(jeuRepository, Mockito.times(1)).save(jeu);

    }


    @Test
    void testFindById() {

        JeuEntity jeu = JeuEntity
                .builder()
                .id(1L)
                .nombreParties(5)
                .statut(StatutJeuEnum.EN_COURS)
                .build();

        Mockito.when(jeuRepository.findById(1L)).thenReturn(Optional.of(jeu));

        Optional<JeuEntity> foundJeu = jeuRepository.findById(1L);

        Assertions.assertTrue(foundJeu.isPresent());
        Assertions.assertEquals(1L,foundJeu.get().getId());
        Assertions.assertEquals(5,foundJeu.get().getNombreParties());
        Assertions.assertEquals(StatutJeuEnum.EN_COURS, foundJeu.get().getStatut());
    }


}
