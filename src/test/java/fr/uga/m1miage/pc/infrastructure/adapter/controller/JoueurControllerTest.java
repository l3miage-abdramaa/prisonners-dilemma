package fr.uga.m1miage.pc.infrastructure.adapter.controller;

import fr.uga.m1miage.pc.application.dto.requests.AbandonRequestDTO;
import fr.uga.m1miage.pc.application.dto.responses.JoueurDTO;
import fr.uga.m1miage.pc.application.service.JoueurService;
import fr.uga.m1miage.pc.domain.enums.StrategieEnum;
import fr.uga.m1miage.pc.domain.model.JoueurEntity;
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

import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

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
                .nom(joueur.getNomJoueur())
                .strategie(joueur.getStrategie())
                .build();
        joueurDTO.setAbandon(true);
        when(joueurService.abandonnerJeu(joueur.getId(),abandonRequestDTO.getStrategie())).thenReturn(joueur);
        ResponseEntity<JoueurDTO> response = joueurController.abandonnerJeu(abandonRequestDTO,joueur.getId());

       assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
       assertThat(response.getBody().getId()).isEqualTo(joueurDTO.getId());
       assertThat(response.getBody().getAbandon()).isEqualTo(joueurDTO.getAbandon());
       assertThat(response.getBody().getStrategie()).isEqualTo(joueurDTO.getStrategie());




    }
}
