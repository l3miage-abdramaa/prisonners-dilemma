package fr.uga.m1miage.pc.jeu.repositories;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import fr.uga.m1miage.pc.jeu.enums.StatutJeuEnum;
import fr.uga.m1miage.pc.jeu.models.JeuEntity;
import fr.uga.m1miage.pc.jeu.repository.JeuRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
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


        when(jeuRepository.save(jeu)).thenReturn(jeu);

        JeuEntity savedJeu = jeuRepository.save(jeu);

        assertNotNull(savedJeu);
        assertEquals(3,savedJeu.getNombreParties());
        assertEquals(StatutJeuEnum.EN_ATTENTE,savedJeu.getStatut());

        verify(jeuRepository, times(1)).save(jeu);

    }


    @Test
    void testFindById() {

        JeuEntity jeu = JeuEntity
                .builder()
                .id(1L)
                .nombreParties(5)
                .statut(StatutJeuEnum.EN_COURS)
                .build();

        when(jeuRepository.findById(1L)).thenReturn(Optional.of(jeu));

        Optional<JeuEntity> foundJeu = jeuRepository.findById(1L);

        assertTrue(foundJeu.isPresent());
        assertEquals(1L,foundJeu.get().getId());
        assertEquals(5,foundJeu.get().getNombreParties());
        assertEquals(StatutJeuEnum.EN_COURS, foundJeu.get().getStatut());
    }


}
