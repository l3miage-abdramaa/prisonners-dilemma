package fr.uga.m1miage.pc.jeu.services;

import fr.uga.m1miage.pc.jeu.models.JeuEntity;
import fr.uga.m1miage.pc.jeu.sse.JeuSseManager;
import fr.uga.m1miage.pc.jeu.enums.StrategieEnum;
import fr.uga.m1miage.pc.jeu.models.JoueurEntity;
import fr.uga.m1miage.pc.jeu.repository.JoueurRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class JoueurServiceTest {


    @Mock
    private JoueurService joueurService;

    @Mock
    private JoueurRepository joueurRepository;


    @Mock
    private JeuSseManager jeuSseManager;

    @BeforeEach public void setup() {
        MockitoAnnotations.openMocks(this);
        joueurService = new JoueurService(jeuSseManager,joueurRepository);
    }


    @Test
    void testAbandonnerJeu() {
        UUID joueurId = UUID.randomUUID();
        StrategieEnum strategie = StrategieEnum.TOUJOURS_COOPERER;

        JeuEntity jeu = JeuEntity.builder().id(1L).nombreParties(2).build();

        JoueurEntity joueur = JoueurEntity.builder()
                .id(joueurId)
                .jeu(jeu)
                .nomJoueur("John")
                .build();

        when(joueurRepository.findById(joueurId)).thenReturn(Optional.of(joueur));
        when(joueurRepository.save(any(JoueurEntity.class))).thenReturn(joueur);

        JoueurEntity result = joueurService.abandonnerJeu(joueurId, strategie);

        assertNotNull(result);
        assertEquals(strategie, result.getStrategie());
        verify(joueurRepository, times(1)).findById(joueurId);
        verify(joueurRepository, times(1)).save(joueur);
        verify(jeuSseManager).notifier(jeu.getId());
    }



    @Test
    void abandonnerJeu_ShouldReturnNull_WhenJoueurDoesNotExist() {

        JoueurEntity joueur = JoueurEntity.builder()
                .nomJoueur("Abdraman")
                .strategie(null)
                .build();
        when(joueurRepository.findById(joueur.getId())).thenReturn(Optional.empty());
        StrategieEnum strategie = StrategieEnum.DONNANT_DONNANT;
        JoueurEntity result = joueurService.abandonnerJeu(joueur.getId(), strategie);
        assertNull(result);
        verify(joueurRepository, never()).save(any(JoueurEntity.class));
    }



}



