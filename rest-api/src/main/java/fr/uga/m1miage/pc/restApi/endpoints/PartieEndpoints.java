package fr.uga.m1miage.pc.restApi.endpoints;

import fr.uga.m1miage.pc.restApi.requests.CoupRequest;
import fr.uga.m1miage.pc.restApi.response.PartieDetailsDTO;
import fr.uga.m1miage.pc.restApi.response.PartieJoueurDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/parties")
public interface PartieEndpoints {

    @PostMapping("/{idJeu}/joueurs/{idJoueur}/jouer-coup")
    ResponseEntity<PartieJoueurDTO> jouerCoup(
            @PathVariable Long idJeu,
            @PathVariable UUID idJoueur,
            @RequestBody CoupRequest coupRequest
    );

    @GetMapping("{idPartie}/details")
    ResponseEntity<PartieDetailsDTO> recupererDetailsPartie(@PathVariable UUID idPartie);
}
