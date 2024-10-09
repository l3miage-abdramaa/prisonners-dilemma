package fr.uga.m1miage.pc.Jeu.controllers;


import fr.uga.m1miage.pc.Jeu.mappers.JeuMapper;
import fr.uga.m1miage.pc.Jeu.models.JeuEntity;
import fr.uga.m1miage.pc.Jeu.requests.AbandonRequestDTO;
import fr.uga.m1miage.pc.Jeu.requests.JeuConnexionRequestDTO;
import fr.uga.m1miage.pc.Jeu.requests.JeuCreationRequestDTO;
import fr.uga.m1miage.pc.Jeu.response.JeuConnexionResponseDTO;
import fr.uga.m1miage.pc.Jeu.response.JeuCreationResponseDTO;
import fr.uga.m1miage.pc.Jeu.response.JeuDTO;
import fr.uga.m1miage.pc.Jeu.service.JeuService;
import fr.uga.m1miage.pc.Joueur.mappers.JoueurMapper;
import fr.uga.m1miage.pc.Joueur.enums.StrategieEnum;
import fr.uga.m1miage.pc.Joueur.reponse.JoueurDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

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

    @PatchMapping("/{idJoueur}/abandonner")
    public ResponseEntity<JoueurDTO> abandonnerJeu(@RequestBody AbandonRequestDTO abandonRequestDTO, @PathVariable UUID idJoueur) {
        jeuService.abandonnerJeu(idJoueur, abandonRequestDTO.getStrategie());
        return ResponseEntity.ok("Le joueur a abandonn� le jeu avec la strat�gie " + strategie);

    }

}

