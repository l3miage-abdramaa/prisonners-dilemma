package fr.uga.m1miage.pc.jeu.sse;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class JeuSseManager {

    private Map<String, Map<String, SseEmitter>> mapJeuSseEmitters = new ConcurrentHashMap<>();
    private static JeuSseManager INSTANCE;

    public SseEmitter creerNouveauSse(Long idJeu, String idJoueur) throws IOException {
        Map<String, SseEmitter> mapJoueurSseEmitters = getMapJoueurSseEmitters(idJeu);
        if (mapJoueurSseEmitters == null) {
            mapJoueurSseEmitters = new ConcurrentHashMap<>();      
        }

        SseEmitter joueurSseEmitter = getJoueurSseEmitter(idJoueur, mapJoueurSseEmitters);
        if (joueurSseEmitter != null) {
            joueurSseEmitter.complete();
            supprimerJoueurSseEmitter(idJoueur, mapJoueurSseEmitters);
        }
        
        SseEmitter sseEmitter = new SseEmitter(600000L);
        sseEmitter.onCompletion(() -> {
            Map<String, SseEmitter> mapJoueurSseEmitters2 = getMapJoueurSseEmitters(idJeu);
            supprimerJoueurSseEmitter(idJoueur, mapJoueurSseEmitters2);
        });
        sseEmitter.onTimeout(sseEmitter::complete);
        mapJoueurSseEmitters.put(idJoueur, sseEmitter);
        mapJeuSseEmitters.put(idJeu.toString(), mapJoueurSseEmitters);

        sseEmitter.send(SseEmitter.event().name("Id Jeu").data(idJeu));

        return sseEmitter;
    }

    public static JeuSseManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new JeuSseManager();
        }

        return INSTANCE;
    }

    public Map<String, SseEmitter> getMapJoueurSseEmitters(Long idJeu) {
        return mapJeuSseEmitters.get(idJeu.toString());
    }

    public SseEmitter getJoueurSseEmitter(String idJoueur, Map<String, SseEmitter> mapJoueurSseEmitters) {
        return mapJoueurSseEmitters.get(idJoueur);
    }

    public void supprimerJeuSseEmitters(Long idJeu) {
        mapJeuSseEmitters.remove(idJeu.toString());
    }

    private void supprimerJoueurSseEmitter(String idJoueur, Map<String, SseEmitter> mapJoueurSseEmitters) {
        mapJoueurSseEmitters.remove(idJoueur);
    }

    public void notifier(Long idJeu) {
        Map<String, SseEmitter> mapJoueurSseEmitters = getMapJoueurSseEmitters(idJeu);
        try {
            for (Map.Entry<String, SseEmitter> joueurSseEmitter : mapJoueurSseEmitters.entrySet()) {
                joueurSseEmitter.getValue().send(SseEmitter.event().name("message").data("recharger"));
            }
        } catch (IOException e) {
            log.error("Jeu "+idJeu+": Erreur d'envoi d'evenement");
        }
    }

}
