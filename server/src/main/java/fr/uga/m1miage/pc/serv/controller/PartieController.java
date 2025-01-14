package fr.uga.m1miage.pc.serv.controller;


import fr.uga.m1miage.pc.restApi.endpoints.PartieEndpoints;
import fr.uga.m1miage.pc.serv.mappers.GlobalMapper;
import fr.uga.m1miage.pc.serv.models.PartieEntity;
import fr.uga.m1miage.pc.serv.models.PartieJoueurEntity;
import fr.uga.m1miage.pc.restApi.requests.CoupRequest;
import fr.uga.m1miage.pc.restApi.response.PartieDetailsDTO;
import fr.uga.m1miage.pc.restApi.response.PartieJoueurDTO;
import fr.uga.m1miage.pc.serv.services.PartieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
public class PartieController implements PartieEndpoints {


    private final PartieService partieService;


    public PartieController(PartieService partieService) {
        this.partieService = partieService;
    }

    @Override
    public ResponseEntity<PartieJoueurDTO> jouerCoup(
            @PathVariable Long idJeu,
            @PathVariable UUID idJoueur,
            @RequestBody CoupRequest coupRequest
    ) {
        PartieJoueurEntity partieJoueur = partieService.jouerCoup(idJoueur,idJeu,coupRequest.getCoup());
        PartieJoueurDTO partieJoueurDTO = GlobalMapper.INSTANCE.mapPartieJoueurEntityToPartieJoueurDTO(partieJoueur);
        return ResponseEntity.ok(partieJoueurDTO);
    }


    @Override
    public ResponseEntity<PartieDetailsDTO> recupererDetailsPartie(@PathVariable UUID idPartie) {
        PartieEntity partie = partieService.recupererDetailsPartie(idPartie);
        PartieDetailsDTO response = GlobalMapper.INSTANCE.mapPartieEntityToPartieDetailsDTO(partie);
        return  ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
