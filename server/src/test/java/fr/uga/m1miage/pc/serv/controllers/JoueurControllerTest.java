package fr.uga.m1miage.pc.serv.controllers;


import fr.uga.m1miage.pc.serv.controller.JoueurController;
import fr.uga.m1miage.pc.restApi.enums.StrategieEnum;
import fr.uga.m1miage.pc.serv.models.JoueurEntity;
import fr.uga.m1miage.pc.restApi.requests.AbandonRequestDTO;
import fr.uga.m1miage.pc.restApi.response.JoueurDTO;

import fr.uga.m1miage.pc.serv.services.JoueurService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
@TestConfiguration
class JoueurControllerTest {


    @Mock
    private JoueurController joueurController;

    @Mock
    private JoueurService joueurService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        joueurService = Mockito.mock(JoueurService.class);
        joueurController = new JoueurController(joueurService);
    }



    @Test
    void abandonnerJeuTest() {
        JoueurEntity joueur = JoueurEntity
                .builder()
                .id(UUID.fromString("d5ebcdc8-9797-463c-a7bc-9a7159c0d44d"))
                .nomJoueur("Abdraman")
                .abandon(true)
                .strategie(StrategieEnum.DONNANT_DONNANT)
                .build();
        AbandonRequestDTO abandonRequestDTO = AbandonRequestDTO
                .builder()
                .strategie(StrategieEnum.DONNANT_DONNANT)
                .build();

        JoueurDTO joueurDTO = JoueurDTO
                .builder()
                .id(joueur.getId())
                .nomJoueur(joueur.getNomJoueur())
                .strategie(joueur.getStrategie())
                .build();
        joueurDTO.setAbandon(true);
        Mockito.when(joueurService.abandonnerJeu(joueur.getId(),abandonRequestDTO.getStrategie())).thenReturn(joueur);
        ResponseEntity<JoueurDTO> response = joueurController.abandonnerJeu(abandonRequestDTO,joueur.getId());

       Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
       Assertions.assertThat(Objects.requireNonNull(response.getBody()).getId()).isEqualTo(joueurDTO.getId());
       Assertions.assertThat(response.getBody().getAbandon()).isEqualTo(joueurDTO.getAbandon());
       Assertions.assertThat(response.getBody().getStrategie()).isEqualTo(joueurDTO.getStrategie());
    }
}
