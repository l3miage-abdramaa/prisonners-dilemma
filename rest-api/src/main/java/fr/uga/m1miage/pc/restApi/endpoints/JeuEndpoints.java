package fr.uga.m1miage.pc.restApi.endpoints;


import fr.uga.m1miage.pc.restApi.requests.JeuConnexionRequestDTO;
import fr.uga.m1miage.pc.restApi.requests.JeuCreationRequestDTO;
import fr.uga.m1miage.pc.restApi.response.JeuConnexionResponseDTO;
import fr.uga.m1miage.pc.restApi.response.JeuCreationResponseDTO;
import fr.uga.m1miage.pc.restApi.response.JeuDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@RestController
@RequestMapping("jeux")
public interface JeuEndpoints {


    @GetMapping("{idJeu}/joueurs/{idJoueur}/event")
    @CrossOrigin("*")
    SseEmitter eventEmitter(@PathVariable Long idJeu, @PathVariable String idJoueur) throws IOException;


    @PostMapping("/creer-jeu")
    ResponseEntity<JeuCreationResponseDTO> creerJeu(@RequestBody JeuCreationRequestDTO jeuCreationDTO);


    @PostMapping("/joindre-jeu")
    ResponseEntity<JeuConnexionResponseDTO> joindreJeu(@RequestBody JeuConnexionRequestDTO jeuConnexionDTO);

    @GetMapping("{idJeu}/details")
    ResponseEntity<JeuDTO> recupererDetailsJeu(@PathVariable Long idJeu);

}
