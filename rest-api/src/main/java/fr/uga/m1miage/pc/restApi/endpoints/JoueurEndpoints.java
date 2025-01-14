package fr.uga.m1miage.pc.restApi.endpoints;


import fr.uga.m1miage.pc.restApi.requests.AbandonRequestDTO;
import fr.uga.m1miage.pc.restApi.response.JoueurDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/joueurs")
public interface JoueurEndpoints {

    @PatchMapping("/{idJoueur}/abandonner")
    ResponseEntity<JoueurDTO> abandonnerJeu(@RequestBody AbandonRequestDTO abandonRequestDTO, @PathVariable UUID idJoueur);
}
