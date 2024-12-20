package fr.uga.m1miage.pc.jeu.controllers;


import fr.uga.m1miage.pc.jeu.models.JeuEntity;
import fr.uga.m1miage.pc.jeu.requests.JeuConnexionRequestDTO;
import fr.uga.m1miage.pc.jeu.requests.JeuCreationRequestDTO;
import fr.uga.m1miage.pc.jeu.response.JeuConnexionResponseDTO;
import fr.uga.m1miage.pc.jeu.response.JeuCreationResponseDTO;
import fr.uga.m1miage.pc.jeu.response.JeuDTO;
import fr.uga.m1miage.pc.jeu.services.JeuService;
import fr.uga.m1miage.pc.jeu.sse.JeuSseManager;
import fr.uga.m1miage.pc.mappers.GlobalMapper;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Controller
@RequestMapping("/jeux")
public class JeuController {

    private final JeuService jeuService;
    private final JeuSseManager jeuSseManager;


    public JeuController(JeuService jeuService,JeuSseManager jeuSseManager) {
        this.jeuService = jeuService;
        this.jeuSseManager = jeuSseManager;
    }

    @GetMapping("{idJeu}/joueurs/{idJoueur}/event")
    @CrossOrigin("*")
    public SseEmitter eventEmitter(@PathVariable Long idJeu, @PathVariable String idJoueur) throws IOException {
        return jeuSseManager.creerNouveauSse(idJeu, idJoueur);
    }

    @PostMapping("/creer-jeu")
    public ResponseEntity<JeuCreationResponseDTO> creerJeu(@RequestBody JeuCreationRequestDTO jeuCreationDTO) {
        JeuEntity jeu = jeuService.creerJeu(jeuCreationDTO.getPseudoJoueur(),jeuCreationDTO.getNombreParties());
        JeuCreationResponseDTO response = GlobalMapper.INSTANCE.mapJeuEntityToJeuCreationResponseDTO(jeu);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/joindre-jeu")
    public ResponseEntity<JeuConnexionResponseDTO> joindreJeu(@RequestBody JeuConnexionRequestDTO jeuConnexionDTO) {
        JeuEntity jeu =  jeuService.joindreJeu(jeuConnexionDTO.getPseudoJoueur(), jeuConnexionDTO.getCodeJeu());
        JeuConnexionResponseDTO response = GlobalMapper.INSTANCE.mapJeuEntityToJeuConnexionResponseDTO(jeu);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @GetMapping("{idJeu}/details")
    public ResponseEntity<JeuDTO> recupererDetailsJeu(@PathVariable Long idJeu) {
        JeuEntity jeu = jeuService.recupererJeu(idJeu);
        JeuDTO response = GlobalMapper.INSTANCE.mapJeuEntityToJeuDto(jeu);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}

