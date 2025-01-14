package fr.uga.m1miage.pc.serv.controllers;


import fr.uga.m1miage.pc.serv.controller.JeuController;
import fr.uga.m1miage.pc.restApi.enums.StatutJeuEnum;
import fr.uga.m1miage.pc.restApi.enums.StatutPartieEnum;
import fr.uga.m1miage.pc.serv.models.JeuEntity;
import fr.uga.m1miage.pc.serv.models.JoueurEntity;
import fr.uga.m1miage.pc.serv.models.PartieEntity;
import fr.uga.m1miage.pc.restApi.requests.JeuConnexionRequestDTO;
import fr.uga.m1miage.pc.restApi.requests.JeuCreationRequestDTO;
import fr.uga.m1miage.pc.restApi.response.*;
import fr.uga.m1miage.pc.serv.services.JeuService;
import fr.uga.m1miage.pc.serv.sse.JeuSseManager;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;



@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class JeuControllerTest {

        @Mock
        private JeuService jeuService;

        @Mock
        private JeuSseManager jeuSseManager;


        @Mock
        private JeuController jeuController;



        @BeforeEach
        void setUp() {
                MockitoAnnotations.openMocks(this);
                jeuController = new JeuController(jeuService,jeuSseManager);
                JeuEntity jeu = new JeuEntity();
                jeu.setId(1L);
                jeu.setStatut(StatutJeuEnum.EN_COURS);
                jeu.setNombreParties(3);
                Mockito.when(jeuService.creerJeu("Abdraman", 3)).thenReturn(jeu);
        }

        @Test
        void testCreerJeu() {
                JeuCreationRequestDTO requestDTO = JeuCreationRequestDTO.builder()
                                .pseudoJoueur("Player1")
                                .nombreParties(3)
                                .build();

                JeuEntity jeuEntity = JeuEntity.builder()
                                .id(1L)
                                .statut(StatutJeuEnum.EN_ATTENTE)
                                .nombreParties(3)
                                .build();

                Mockito.when(jeuService.creerJeu("Player1", 3)).thenReturn(jeuEntity);

                JeuCreationResponseDTO responseDTO = JeuCreationResponseDTO.builder()
                                .id(1L)
                                .statut(StatutJeuEnum.EN_ATTENTE)
                                .nombreParties(3)
                                .build();


                ResponseEntity<JeuCreationResponseDTO> response = jeuController.creerJeu(requestDTO);


                Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
                Assertions.assertEquals(responseDTO, response.getBody());
        }

        @Test
        void testJoindreJeu() {

                JeuEntity jeuEntity = JeuEntity.builder()
                                .id(57657L)
                                .statut(StatutJeuEnum.EN_COURS)
                                .nombreParties(3)
                                .build();

                JeuConnexionRequestDTO jeuConnexionRequestDTO = JeuConnexionRequestDTO.builder()
                                .codeJeu(57657L)
                                .pseudoJoueur("Gabriel")
                                .build();


                Mockito.when(jeuService.joindreJeu("Gabriel", 57657L)).thenReturn(jeuEntity);

                JeuConnexionResponseDTO jeuConnexionResponseDTO = JeuConnexionResponseDTO.builder()
                                .id(57657L)
                                .statut(StatutJeuEnum.EN_COURS)
                                .build();

                ResponseEntity<JeuConnexionResponseDTO> response = jeuController.joindreJeu(jeuConnexionRequestDTO);


                assert (response != null);
                assert (response.getBody() != null);
                Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
                Assertions.assertEquals(jeuConnexionResponseDTO, response.getBody());
        }

        @Test
        void testRecupererDetailsJeu() {
                JoueurEntity joueur = JoueurEntity.builder()
                                .nomJoueur("Gabriel")
                                .build();
                PartieEntity partie = PartieEntity
                                .builder()
                                .statut(StatutPartieEnum.EN_COURS)
                                .ordre(1)
                                .build();
                JeuEntity jeu = JeuEntity.builder()
                                .id(57657L)
                                .statut(StatutJeuEnum.EN_ATTENTE)
                                .joueurs(List.of(joueur))
                                .parties(List.of(partie))
                                .nombreParties(1)
                                .build();
                PartieDTO partieDTO = PartieDTO
                                .builder()
                                .id(partie.getId())
                                .statut(partie.getStatut())
                                .ordre(partie.getOrdre())
                                .build();
                JoueurDTO joueurDTO = JoueurDTO
                                .builder()
                                .nomJoueur("Gabriel")
                                .build();

                JeuDTO jeuDTO = JeuDTO
                                .builder()
                                .id(jeu.getId())
                                .nombreParties(1)
                                .statut(jeu.getStatut())
                                .joueurs(List.of(joueurDTO))
                                .parties(List.of(partieDTO))
                                .build();

                Mockito.when(jeuService.recupererJeu(jeu.getId())).thenReturn(jeu);
                ResponseEntity<JeuDTO> res = jeuController.recupererDetailsJeu(jeu.getId());
                Assertions.assertEquals(HttpStatus.OK, res.getStatusCode());
                AssertionsForClassTypes.assertThat(res.getBody().getId()).isEqualTo(jeuDTO.getId());
                AssertionsForClassTypes.assertThat(res.getBody().getNombreParties()).isEqualTo(jeuDTO.getNombreParties());
                AssertionsForClassTypes.assertThat(res.getBody().getStatut()).isEqualTo(jeuDTO.getStatut());
                AssertionsForClassTypes.assertThat(res.getBody().getParties().get(0).getId()).isEqualTo(jeuDTO.getParties().get(0).getId());
                AssertionsForClassTypes.assertThat(res.getBody().getJoueurs().get(0).getId()).isEqualTo(jeuDTO.getJoueurs().get(0).getId());
                AssertionsForClassTypes.assertThat(res.getBody().getJoueurs().get(0).getNomJoueur()).isEqualTo(jeuDTO.getJoueurs().get(0).getNomJoueur());
        }

        @Test
        void testEventEmitter() throws Exception {
                Long idJeu = 1L;
                String idJoueur = "joueur1";
                SseEmitter sseEmitter = new SseEmitter();
                Mockito.when(jeuSseManager.creerNouveauSse(idJeu, idJoueur)).thenReturn(sseEmitter);
                SseEmitter result = jeuController.eventEmitter(idJeu, idJoueur);
                Assertions.assertNotNull(result);
        }

}
