package fr.uga.m1miage.pc.jeu.controllers;


import fr.uga.m1miage.pc.jeu.mappers.JeuMapper;
import fr.uga.m1miage.pc.jeu.models.JeuEntity;
import fr.uga.m1miage.pc.jeu.requests.JeuConnexionRequestDTO;
import fr.uga.m1miage.pc.jeu.requests.JeuCreationRequestDTO;
import fr.uga.m1miage.pc.jeu.response.JeuConnexionResponseDTO;
import fr.uga.m1miage.pc.jeu.response.JeuCreationResponseDTO;
import fr.uga.m1miage.pc.jeu.response.JeuDTO;
import fr.uga.m1miage.pc.jeu.services.JeuService;
import fr.uga.m1miage.pc.joueur.mappers.JoueurMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jeux")
public class JeuController {
    @Autowired
    private JeuService jeuService;

    @Autowired
    private JeuMapper jeuMapper;

    @Autowired
    private JoueurMapper joueurMapper;


    @PostMapping("/creer-jeu")
    public ResponseEntity<JeuCreationResponseDTO> creerJeu(@RequestBody JeuCreationRequestDTO jeuCreationDTO) {
        JeuEntity jeu = jeuService.creerJeu(jeuCreationDTO.getPseudoJoueur(),jeuCreationDTO.getNombreParties());
        JeuCreationResponseDTO response = jeuMapper.mapJeuEntityToJeuCreationResponseDTO(jeu);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/joindre-jeu")
    public ResponseEntity<JeuConnexionResponseDTO> joindreJeu(@RequestBody JeuConnexionRequestDTO jeuConnexionDTO) {
      JeuEntity jeu =  jeuService.joindreJeu(jeuConnexionDTO.getPseudoJoueur(), jeuConnexionDTO.getCodeJeu());
        JeuConnexionResponseDTO response = jeuMapper.mapJeuEntityToJeuConnexionResponseDTO(jeu);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @GetMapping("{idJeu}/details")
    public ResponseEntity<JeuDTO> recupererDetailsJeu(@PathVariable Long idJeu) {
        JeuEntity jeu = jeuService.recupererJeu(idJeu);
        JeuDTO response = jeuMapper.mapEntityToDTO(jeu);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}

