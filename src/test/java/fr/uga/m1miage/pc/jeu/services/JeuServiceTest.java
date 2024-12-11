package fr.uga.m1miage.pc.jeu.services;

import fr.uga.m1miage.pc.jeu.enums.StatutJeuEnum;
import fr.uga.m1miage.pc.jeu.models.JeuEntity;
import fr.uga.m1miage.pc.jeu.repository.JeuRepository;
import fr.uga.m1miage.pc.jeu.sse.JeuSseManager;
import fr.uga.m1miage.pc.jeu.models.JoueurEntity;
import fr.uga.m1miage.pc.jeu.repository.JoueurRepository;
import fr.uga.m1miage.pc.jeu.enums.StatutPartieEnum;
import fr.uga.m1miage.pc.jeu.models.PartieEntity;
import fr.uga.m1miage.pc.jeu.repository.PartieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;


import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class JeuServiceTest {

    @Mock
    private JeuService jeuService;

    @Mock
    private JoueurRepository joueurRepository;

    @Mock
    private JeuRepository jeuRepository;

    @Mock
    private PartieRepository partieRepository;


    @Mock
    private JeuSseManager jeuSseManager;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        jeuService = new JeuService(joueurRepository,jeuRepository,partieRepository,jeuSseManager);
    }


    @Test
    void testCreerJeu() {

        String nomJoueur = "John";
        int nombreParties = 5;
        JeuEntity jeu = JeuEntity.builder()
                .nombreParties(nombreParties)
                .statut(StatutJeuEnum.EN_ATTENTE)
                .build();
        jeuRepository.save(jeu);

        JoueurEntity joueur = JoueurEntity.builder()
                .nomJoueur(nomJoueur)
                .jeu(jeu)
                .build();

        when(jeuRepository.save(any(JeuEntity.class))).thenReturn(jeu);
        when(joueurRepository.save(any(JoueurEntity.class))).thenReturn(joueur);


        JeuEntity resultat = jeuService.creerJeu(nomJoueur, nombreParties);

        assertNotNull(resultat);
        assertEquals(nombreParties, resultat.getNombreParties());
        assertEquals(StatutJeuEnum.EN_ATTENTE, resultat.getStatut());
        verify(jeuRepository, times(2)).save(any(JeuEntity.class));
        verify(joueurRepository, times(1)).save(any(JoueurEntity.class));
    }

    @Test
    void testJoindreJeu() {

        Long jeuId = 1L;
        String pseudo = "John";

        JeuEntity jeu = JeuEntity.builder()
                .id(jeuId)
                .statut(StatutJeuEnum.EN_ATTENTE)
                .nombreParties(3)
                .build();

        JoueurEntity secondJoueur = JoueurEntity.builder()
                .nomJoueur(pseudo)
                .jeu(jeu)
                .build();

        PartieEntity partie = PartieEntity.builder()
                .jeu(jeu)
                .statut(StatutPartieEnum.EN_COURS)
                .build();

        when(jeuRepository.findById(jeuId)).thenReturn(Optional.of(jeu));
        when(joueurRepository.save(any(JoueurEntity.class))).thenReturn(secondJoueur);
        when(partieRepository.save(any(PartieEntity.class))).thenReturn(partie);
        when(jeuRepository.save(jeu)).thenReturn(jeu);

        JeuEntity result = jeuService.joindreJeu(pseudo, jeuId);


        assertNotNull(result);
        assertEquals(StatutJeuEnum.EN_COURS, result.getStatut());
        verify(joueurRepository, times(1)).save(any(JoueurEntity.class));
        verify(partieRepository, times(1)).save(any(PartieEntity.class));
        verify(jeuRepository, times(1)).save(jeu);
        verify(jeuSseManager).notifier(jeuId);
    }


    @Test
    void joindreJeuFailed() {
        JeuEntity jeu = JeuEntity
                .builder()
                .id(1L)
                .statut(StatutJeuEnum.EN_COURS)
                .build();

        Long idJeu = 1L;
        String pseudo = "JoueurTest";

        when(jeuRepository.findById(jeu.getId())).thenReturn(Optional.of(jeu));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            jeuService.joindreJeu(pseudo, idJeu);
        });

        assertEquals("Le nombre de joueurs est atteint", exception.getMessage());


        verify(joueurRepository, never()).save(any(JoueurEntity.class));
        verify(partieRepository, never()).save(any(PartieEntity.class));
        verify(jeuRepository, never()).save(jeu);
    }


    @Test
    void testRecupererJeu() {
        Long jeuId = 1L;

        JeuEntity jeu = JeuEntity.builder()
                .id(jeuId)
                .statut(StatutJeuEnum.EN_ATTENTE)
                .nombreParties(3)
                .build();

        when(jeuRepository.findById(jeuId)).thenReturn(Optional.of(jeu));

        JeuEntity result = jeuService.recupererJeu(jeuId);

        assertNotNull(result);
        assertEquals(jeuId, result.getId());
        verify(jeuRepository, times(1)).findById(jeuId);
    }


    @Test
    void testJoindreJeu_JoueursMaxAtteints() {
        JeuEntity jeu = JeuEntity.builder()
                .id(1L)
                .nombreParties(3)
                .statut(StatutJeuEnum.EN_COURS)
                .build();

        when(jeuRepository.findById(1L)).thenReturn(Optional.of(jeu));
        assertThrows(IllegalArgumentException.class, () -> {
            jeuService.joindreJeu("Alice", 1L);
        });
        verify(joueurRepository, never()).save(any(JoueurEntity.class));
    }


    @Test
    void testJoindreJeu_JeuNonTrouve() {
        assertThrows(NoSuchElementException.class, () -> {
            jeuService.joindreJeu("Bob", 999L);
        });
    }

    @Test
    void testJoindreJeu_JeuNonExistant() {

        Long id = 1L;
        when(jeuRepository.findById(id)).thenReturn(Optional.empty());


        assertThrows(NoSuchElementException.class, () -> jeuService.joindreJeu("Doe", id));
    }

    @Test
    void testJoindreJeu_DejaCommence() {

        Long id = 1L;
        JeuEntity jeu = JeuEntity.builder()
                .statut(StatutJeuEnum.EN_COURS)
                .build();

        when(jeuRepository.findById(id)).thenReturn(Optional.of(jeu));


        assertThrows(IllegalArgumentException.class, () -> jeuService.joindreJeu("Doe", id));
    }

    @Test
    void testRecupererJeu_Success() {

        Long idJeu = 1L;
        JeuEntity jeu = JeuEntity.builder().id(idJeu).build();
        when(jeuRepository.findById(idJeu)).thenReturn(Optional.of(jeu));


        JeuEntity result = jeuService.recupererJeu(idJeu);


        assertNotNull(result);
        verify(jeuRepository, times(1)).findById(idJeu);
    }

    @Test
    void testRecupererJeu_JeuNonExistant() {

        Long idJeu = 1L;
        when(jeuRepository.findById(idJeu)).thenReturn(Optional.empty());


        assertThrows(NoSuchElementException.class, () -> jeuService.recupererJeu(idJeu));
    }


}





