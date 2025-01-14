package fr.uga.m1miage.pc.serv.services;


import fr.uga.m1miage.pc.restApi.enums.StatutJeuEnum;
import fr.uga.m1miage.pc.restApi.enums.StatutPartieEnum;
import fr.uga.m1miage.pc.serv.models.JeuEntity;
import fr.uga.m1miage.pc.serv.models.JoueurEntity;
import fr.uga.m1miage.pc.serv.models.PartieEntity;
import fr.uga.m1miage.pc.serv.repositories.JeuRepository;
import fr.uga.m1miage.pc.serv.repositories.JoueurRepository;
import fr.uga.m1miage.pc.serv.repositories.PartieRepository;
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


import java.util.NoSuchElementException;
import java.util.Optional;




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

        Mockito.when(jeuRepository.save(ArgumentMatchers.any(JeuEntity.class))).thenReturn(jeu);
        Mockito.when(joueurRepository.save(ArgumentMatchers.any(JoueurEntity.class))).thenReturn(joueur);


        JeuEntity resultat = jeuService.creerJeu(nomJoueur, nombreParties);

        Assertions.assertNotNull(resultat);
        Assertions.assertEquals(nombreParties, resultat.getNombreParties());
        Assertions.assertEquals(StatutJeuEnum.EN_ATTENTE, resultat.getStatut());
        Mockito.verify(jeuRepository, Mockito.times(2)).save(ArgumentMatchers.any(JeuEntity.class));
        Mockito.verify(joueurRepository, Mockito.times(1)).save(ArgumentMatchers.any(JoueurEntity.class));
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

        Mockito.when(jeuRepository.findById(jeuId)).thenReturn(Optional.of(jeu));
        Mockito.when(joueurRepository.save(ArgumentMatchers.any(JoueurEntity.class))).thenReturn(secondJoueur);
        Mockito.when(partieRepository.save(ArgumentMatchers.any(PartieEntity.class))).thenReturn(partie);
        Mockito.when(jeuRepository.save(jeu)).thenReturn(jeu);

        JeuEntity result = jeuService.joindreJeu(pseudo, jeuId);


        Assertions.assertNotNull(result);
        Assertions.assertEquals(StatutJeuEnum.EN_COURS, result.getStatut());
        Mockito.verify(joueurRepository, Mockito.times(1)).save(ArgumentMatchers.any(JoueurEntity.class));
        Mockito.verify(partieRepository, Mockito.times(1)).save(ArgumentMatchers.any(PartieEntity.class));
        Mockito.verify(jeuRepository, Mockito.times(1)).save(jeu);
        Mockito.verify(jeuSseManager).notifier(jeuId);
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

        Mockito.when(jeuRepository.findById(jeu.getId())).thenReturn(Optional.of(jeu));

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            jeuService.joindreJeu(pseudo, idJeu);
        });

        Assertions.assertEquals("Le nombre de joueurs est atteint", exception.getMessage());


        Mockito.verify(joueurRepository, Mockito.never()).save(ArgumentMatchers.any(JoueurEntity.class));
        Mockito.verify(partieRepository, Mockito.never()).save(ArgumentMatchers.any(PartieEntity.class));
        Mockito.verify(jeuRepository, Mockito.never()).save(jeu);
    }


    @Test
    void testRecupererJeu() {
        Long jeuId = 1L;

        JeuEntity jeu = JeuEntity.builder()
                .id(jeuId)
                .statut(StatutJeuEnum.EN_ATTENTE)
                .nombreParties(3)
                .build();

        Mockito.when(jeuRepository.findById(jeuId)).thenReturn(Optional.of(jeu));

        JeuEntity result = jeuService.recupererJeu(jeuId);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(jeuId, result.getId());
        Mockito.verify(jeuRepository, Mockito.times(1)).findById(jeuId);
    }


    @Test
    void testJoindreJeu_JoueursMaxAtteints() {
        JeuEntity jeu = JeuEntity.builder()
                .id(1L)
                .nombreParties(3)
                .statut(StatutJeuEnum.EN_COURS)
                .build();

        Mockito.when(jeuRepository.findById(1L)).thenReturn(Optional.of(jeu));
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            jeuService.joindreJeu("Alice", 1L);
        });
        Mockito.verify(joueurRepository, Mockito.never()).save(ArgumentMatchers.any(JoueurEntity.class));
    }


    @Test
    void testJoindreJeu_JeuNonTrouve() {
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            jeuService.joindreJeu("Bob", 999L);
        });
    }

    @Test
    void testJoindreJeu_JeuNonExistant() {

        Long id = 1L;
        Mockito.when(jeuRepository.findById(id)).thenReturn(Optional.empty());


        Assertions.assertThrows(NoSuchElementException.class, () -> jeuService.joindreJeu("Doe", id));
    }

    @Test
    void testJoindreJeu_DejaCommence() {

        Long id = 1L;
        JeuEntity jeu = JeuEntity.builder()
                .statut(StatutJeuEnum.EN_COURS)
                .build();

        Mockito.when(jeuRepository.findById(id)).thenReturn(Optional.of(jeu));


        Assertions.assertThrows(IllegalArgumentException.class, () -> jeuService.joindreJeu("Doe", id));
    }

    @Test
    void testRecupererJeu_Success() {

        Long idJeu = 1L;
        JeuEntity jeu = JeuEntity.builder().id(idJeu).build();
        Mockito.when(jeuRepository.findById(idJeu)).thenReturn(Optional.of(jeu));


        JeuEntity result = jeuService.recupererJeu(idJeu);


        Assertions.assertNotNull(result);
        Mockito.verify(jeuRepository, Mockito.times(1)).findById(idJeu);
    }

    @Test
    void testRecupererJeu_JeuNonExistant() {

        Long idJeu = 1L;
        Mockito.when(jeuRepository.findById(idJeu)).thenReturn(Optional.empty());


        Assertions.assertThrows(NoSuchElementException.class, () -> jeuService.recupererJeu(idJeu));
    }


}





