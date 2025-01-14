package fr.uga.m1miage.pc.serv.controller;




import fr.uga.m1miage.pc.restApi.endpoints.JeuEndpoints;
import fr.uga.m1miage.pc.serv.mappers.GlobalMapper;
import fr.uga.m1miage.pc.serv.models.JeuEntity;
import fr.uga.m1miage.pc.restApi.requests.JeuConnexionRequestDTO;
import fr.uga.m1miage.pc.restApi.requests.JeuCreationRequestDTO;
import fr.uga.m1miage.pc.restApi.response.JeuConnexionResponseDTO;
import fr.uga.m1miage.pc.restApi.response.JeuCreationResponseDTO;
import fr.uga.m1miage.pc.restApi.response.JeuDTO;
import fr.uga.m1miage.pc.serv.services.JeuService;
import fr.uga.m1miage.pc.serv.sse.JeuSseManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;



@Controller
public class JeuController implements JeuEndpoints {

    private final JeuService jeuService;
    private final JeuSseManager jeuSseManager;


    public JeuController(JeuService jeuService,JeuSseManager jeuSseManager) {
        this.jeuService = jeuService;
        this.jeuSseManager = jeuSseManager;
    }


    @Override
    public SseEmitter eventEmitter(@PathVariable Long idJeu, @PathVariable String idJoueur) throws IOException {
        return jeuSseManager.creerNouveauSse(idJeu, idJoueur);
    }

    @Override
    public ResponseEntity<JeuCreationResponseDTO> creerJeu(@RequestBody JeuCreationRequestDTO jeuCreationDTO) {
        JeuEntity jeu = jeuService.creerJeu(jeuCreationDTO.getPseudoJoueur(),jeuCreationDTO.getNombreParties());
        JeuCreationResponseDTO response = GlobalMapper.INSTANCE.mapJeuEntityToJeuCreationResponseDTO(jeu);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<JeuConnexionResponseDTO> joindreJeu(@RequestBody JeuConnexionRequestDTO jeuConnexionDTO) {
        JeuEntity jeu =  jeuService.joindreJeu(jeuConnexionDTO.getPseudoJoueur(), jeuConnexionDTO.getCodeJeu());
        JeuConnexionResponseDTO response = GlobalMapper.INSTANCE.mapJeuEntityToJeuConnexionResponseDTO(jeu);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }



    @Override
    public ResponseEntity<JeuDTO> recupererDetailsJeu(@PathVariable Long idJeu) {
        JeuEntity jeu = jeuService.recupererJeu(idJeu);
        JeuDTO response = GlobalMapper.INSTANCE.mapJeuEntityToJeuDto(jeu);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}

