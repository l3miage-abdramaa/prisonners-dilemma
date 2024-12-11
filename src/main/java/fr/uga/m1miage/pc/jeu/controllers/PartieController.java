package fr.uga.m1miage.pc.jeu.controllers;

import fr.uga.m1miage.pc.mappers.GlobalMapper;
import fr.uga.m1miage.pc.jeu.models.PartieEntity;
import fr.uga.m1miage.pc.jeu.models.PartieJoueurEntity;
import fr.uga.m1miage.pc.jeu.requests.CoupRequest;
import fr.uga.m1miage.pc.jeu.response.PartieDetailsDTO;
import fr.uga.m1miage.pc.jeu.response.PartieJoueurDTO;
import fr.uga.m1miage.pc.jeu.services.PartieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/parties")
public class PartieController {


    private final PartieService partieService;


    public PartieController(PartieService partieService) {
        this.partieService = partieService;
    }

    @PostMapping("/{idJeu}/joueurs/{idJoueur}/jouer-coup")
    public ResponseEntity<PartieJoueurDTO> jouerCoup(
            @PathVariable Long idJeu,
            @PathVariable UUID idJoueur,
            @RequestBody CoupRequest coupRequest
    ) {
        PartieJoueurEntity partieJoueur = partieService.jouerCoup(idJoueur,idJeu,coupRequest.getCoup());
        PartieJoueurDTO partieJoueurDTO = GlobalMapper.INSTANCE.mapPartieJoueurEntityToPartieJoueurDTO(partieJoueur);
        return ResponseEntity.ok(partieJoueurDTO);
    }


    @GetMapping("{idPartie}/details")
    public ResponseEntity<PartieDetailsDTO> recupererDetailsPartie(@PathVariable UUID idPartie) {
        PartieEntity partie = partieService.recupererDetailsPartie(idPartie);
        PartieDetailsDTO response = GlobalMapper.INSTANCE.mapPartieEntityToPartieDetailsDTO(partie);
        return  ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
