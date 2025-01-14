package fr.uga.m1miage.pc.serv.services;



import fr.uga.m1miage.pc.restApi.enums.StrategieEnum;
import fr.uga.m1miage.pc.serv.models.JeuEntity;
import fr.uga.m1miage.pc.serv.models.JoueurEntity;
import fr.uga.m1miage.pc.serv.repositories.JoueurRepository;
import fr.uga.m1miage.pc.serv.sse.JeuSseManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Optional;
import java.util.UUID;
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

        Mockito.when(joueurRepository.findById(joueurId)).thenReturn(Optional.of(joueur));
        Mockito.when(joueurRepository.save(ArgumentMatchers.any(JoueurEntity.class))).thenReturn(joueur);

        JoueurEntity result = joueurService.abandonnerJeu(joueurId, strategie);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(strategie, result.getStrategie());
        Mockito.verify(joueurRepository, Mockito.times(1)).findById(joueurId);
        Mockito.verify(joueurRepository, Mockito.times(1)).save(joueur);
        Mockito.verify(jeuSseManager).notifier(jeu.getId());
    }



    @Test
    void abandonnerJeu_ShouldReturnNull_WhenJoueurDoesNotExist() {

        JoueurEntity joueur = JoueurEntity.builder()
                .nomJoueur("Abdraman")
                .strategie(null)
                .build();
        Mockito.when(joueurRepository.findById(joueur.getId())).thenReturn(Optional.empty());
        StrategieEnum strategie = StrategieEnum.DONNANT_DONNANT;
        JoueurEntity result = joueurService.abandonnerJeu(joueur.getId(), strategie);
        Assertions.assertNull(result);
        Mockito.verify(joueurRepository, Mockito.never()).save(ArgumentMatchers.any(JoueurEntity.class));
    }



}



