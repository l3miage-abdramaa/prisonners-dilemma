package fr.uga.m1miage.pc.serv.controller;


import fr.uga.m1miage.pc.restApi.endpoints.JoueurEndpoints;
import fr.uga.m1miage.pc.serv.mappers.GlobalMapper;
import fr.uga.m1miage.pc.serv.models.JoueurEntity;
import fr.uga.m1miage.pc.restApi.requests.AbandonRequestDTO;
import fr.uga.m1miage.pc.restApi.response.JoueurDTO;
import fr.uga.m1miage.pc.serv.services.JoueurService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
public class JoueurController implements JoueurEndpoints {
    private final JoueurService joueurService;

    public JoueurController(JoueurService joueurService) {
        this.joueurService = joueurService;
    }



    @Override
    public ResponseEntity<JoueurDTO> abandonnerJeu(@RequestBody AbandonRequestDTO abandonRequestDTO, @PathVariable UUID idJoueur) {
        JoueurEntity joueur = joueurService.abandonnerJeu(idJoueur, abandonRequestDTO.getStrategie());
        JoueurDTO response = GlobalMapper.INSTANCE.mapJoueurEntityToJoueurDTO(joueur);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
