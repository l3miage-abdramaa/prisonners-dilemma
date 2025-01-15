package fr.uga.m1miage.pc.infrastructure.adapter.controller;

import fr.uga.m1miage.pc.application.dto.requests.AbandonRequestDTO;
import fr.uga.m1miage.pc.domain.model.JoueurEntity;
import fr.uga.m1miage.pc.application.dto.responses.JoueurDTO;
import fr.uga.m1miage.pc.application.service.JoueurService;
import fr.uga.m1miage.pc.application.mapper.GlobalMapper;
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
