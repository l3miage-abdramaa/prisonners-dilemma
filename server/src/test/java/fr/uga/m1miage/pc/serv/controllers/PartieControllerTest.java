package fr.uga.m1miage.pc.serv.controllers;


import fr.uga.m1miage.pc.serv.controller.PartieController;
import fr.uga.m1miage.pc.restApi.enums.CoupEnum;
import fr.uga.m1miage.pc.restApi.enums.StatutJeuEnum;
import fr.uga.m1miage.pc.restApi.enums.StatutPartieEnum;
import fr.uga.m1miage.pc.serv.models.JeuEntity;
import fr.uga.m1miage.pc.serv.models.JoueurEntity;
import fr.uga.m1miage.pc.serv.models.PartieEntity;
import fr.uga.m1miage.pc.serv.models.PartieJoueurEntity;
import fr.uga.m1miage.pc.serv.repositories.JoueurRepository;
import fr.uga.m1miage.pc.restApi.requests.CoupRequest;
import fr.uga.m1miage.pc.restApi.response.JoueurDTO;
import fr.uga.m1miage.pc.restApi.response.PartieDTO;
import fr.uga.m1miage.pc.restApi.response.PartieDetailsDTO;
import fr.uga.m1miage.pc.restApi.response.PartieJoueurDTO;
import fr.uga.m1miage.pc.serv.services.PartieService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;



@ExtendWith(MockitoExtension.class)
@TestConfiguration
class PartieControllerTest {


    private PartieService partieService;

    @Mock
    private PartieController partieController;

    @Mock
    private JoueurRepository joueurRepository;


    @BeforeEach
    void setup() {
        partieService = Mockito.mock(PartieService.class);
        partieController = new PartieController(partieService);
    }

    @Test
    void jouerCoupTest() {
        JeuEntity jeu = JeuEntity.builder()
                .id(1L)
                .statut(StatutJeuEnum.EN_ATTENTE)
                .nombreParties(3)
                .build();
        CoupRequest coupRequest = CoupRequest
                .builder()
                .coup(CoupEnum.COOPERER)
                .build();
        JoueurEntity joueur = JoueurEntity
                .builder()
                .id(UUID.fromString("d5ebcdc8-9797-463c-a7bc-9a7159c0d44d"))
                .nomJoueur("Abdraman")
                .jeu(jeu)
                .build();
        PartieJoueurEntity partieJoueur = PartieJoueurEntity
                .builder()
                .joueur(joueur)
                .score(1)
                .build();
        Mockito.when(partieService.jouerCoup(joueur.getId(),jeu.getId(),coupRequest.getCoup())).thenReturn(partieJoueur);
        ResponseEntity<PartieJoueurDTO> response = partieController.jouerCoup(jeu.getId(), joueur.getId(),coupRequest);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody().getScore()).isEqualTo(1);
    }




    @Test
    void testRecupererDetailsPartie() {
        JoueurEntity joueur = JoueurEntity
                .builder()
                .nomJoueur("Abdraman")
                .build();
        PartieEntity partie = PartieEntity
                .builder()
                .ordre(1)
                .statut(StatutPartieEnum.EN_COURS)
                .build();
        PartieDTO partieDTO = PartieDTO
                .builder()
                .statut(partie.getStatut())
                .ordre(partie.getOrdre())
                .id(partie.getId())
                .build();
        JoueurDTO joueurDTO = JoueurDTO
                .builder()
                .id(joueur.getId())
                .nomJoueur(joueur.getNomJoueur())
                .build();
        PartieJoueurEntity partieJoueur1 = PartieJoueurEntity
                .builder()
                .partie(partie)
                .score(2)
                .joueur(joueur)
                .coup(CoupEnum.COOPERER)
                .build();

        PartieJoueurDTO partieJoueur = PartieJoueurDTO
                .builder()
                .score(partieJoueur1.getScore())
                .partie(partieDTO)
                .joueur(joueurDTO)
                .coup(String.valueOf(partieJoueur1.getCoup()))
                .build();


        PartieDetailsDTO partieDetailsDTO = PartieDetailsDTO
                .builder()
                .id(partie.getId())
                .statut(StatutPartieEnum.EN_COURS)
                .ordre(1)
                .build();
        partie.setPartiesJoueur(List.of(partieJoueur1));
        partieDetailsDTO.setPartiesJoueur(List.of(partieJoueur));
        joueur.setPartieJoueurs(List.of(partieJoueur1));
        Mockito.when(partieService.recupererDetailsPartie(partie.getId())).thenReturn(partie);
        ResponseEntity<PartieDetailsDTO> response = partieController.recupererDetailsPartie(partie.getId());
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody().getId()).isEqualTo(partieDetailsDTO.getId());
        Assertions.assertThat(response.getBody().getOrdre()).isEqualTo(partieDetailsDTO.getOrdre());
        Assertions.assertThat(response.getBody().getStatut()).isEqualTo(partieDetailsDTO.getStatut());
        Assertions.assertThat(response.getBody().getPartiesJoueur().get(0).getPartie().getId()).isEqualTo(partieDetailsDTO.getPartiesJoueur().get(0).getPartie().getId());
        Assertions.assertThat(response.getBody().getPartiesJoueur().get(0).getPartie().getStatut()).isEqualTo(partieDetailsDTO.getPartiesJoueur().get(0).getPartie().getStatut());
        Assertions.assertThat(response.getBody().getPartiesJoueur().get(0).getPartie().getOrdre()).isEqualTo(partieDetailsDTO.getPartiesJoueur().get(0).getPartie().getOrdre());
        Assertions.assertThat(response.getBody().getPartiesJoueur().get(0).getCoup()).isEqualTo(partieDetailsDTO.getPartiesJoueur().get(0).getCoup());
        Assertions.assertThat(response.getBody().getPartiesJoueur().get(0).getJoueur().getId()).isEqualTo(partieDetailsDTO.getPartiesJoueur().get(0).getJoueur().getId());
        Assertions.assertThat(response.getBody().getPartiesJoueur().get(0).getPartie().getId()).isEqualTo(partie.getPartiesJoueur().get(0).getId());
        Assertions.assertThat(response.getBody().getPartiesJoueur().get(0).getPartie().getId()).isEqualTo(joueur.getPartieJoueurs().get(0).getPartie().getId());
    }




}
