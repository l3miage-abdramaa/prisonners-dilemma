package fr.uga.m1miage.pc.jeu.controllers;

import fr.uga.m1miage.pc.jeu.requests.AbandonRequestDTO;
import fr.uga.m1miage.pc.jeu.models.JoueurEntity;
import fr.uga.m1miage.pc.jeu.response.JoueurDTO;
import fr.uga.m1miage.pc.jeu.services.JoueurService;
import fr.uga.m1miage.pc.mappers.GlobalMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/joueurs")
public class JoueurController {
    private final JoueurService joueurService;

    public JoueurController(JoueurService joueurService) {
        this.joueurService = joueurService;
    }


    @PatchMapping("/{idJoueur}/abandonner")
    public ResponseEntity<JoueurDTO> abandonnerJeu(@RequestBody AbandonRequestDTO abandonRequestDTO, @PathVariable UUID idJoueur) {
        JoueurEntity joueur = joueurService.abandonnerJeu(idJoueur, abandonRequestDTO.getStrategie());
        JoueurDTO response = GlobalMapper.INSTANCE.mapJoueurEntityToJoueurDTO(joueur);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
