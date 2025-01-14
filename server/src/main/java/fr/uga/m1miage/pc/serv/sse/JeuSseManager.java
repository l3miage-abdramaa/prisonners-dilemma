package fr.uga.m1miage.pc.serv.sse;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

import lombok.extern.slf4j.Slf4j;
import  org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;


@Slf4j
@Component
public class JeuSseManager {
    public AtomicReference<Map<String, Map<String, SseEmitter>>> getMapJeuSseEmitters() {
        return mapJeuSseEmitters;
    }

    public void setMapJeuSseEmitters(AtomicReference<Map<String, Map<String, SseEmitter>>> mapJeuSseEmitters) {
        this.mapJeuSseEmitters = mapJeuSseEmitters;
    }

    private AtomicReference<Map<String, Map<String, SseEmitter>>> mapJeuSseEmitters;

    public JeuSseManager() {
        this.mapJeuSseEmitters = new AtomicReference<>(new ConcurrentHashMap<>());
    }


    public synchronized SseEmitter creerNouveauSse(Long idJeu, String idJoueur) throws IOException {
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
            if (mapJoueurSseEmitters2.size() == 0) {
                supprimerJeuSseEmitters(idJeu);
            }
        });
        sseEmitter.onTimeout(sseEmitter::complete);
        mapJoueurSseEmitters.put(idJoueur, sseEmitter);
        mapJeuSseEmitters.get().put(idJeu.toString(), mapJoueurSseEmitters);

        sseEmitter.send(SseEmitter.event().name("Id Jeu").data(idJeu));

        return sseEmitter;
    }

    public Map<String, SseEmitter> getMapJoueurSseEmitters(Long idJeu) {
        return mapJeuSseEmitters.get().get(idJeu.toString());
    }

    public SseEmitter getJoueurSseEmitter(String idJoueur, Map<String, SseEmitter> mapJoueurSseEmitters) {
        return mapJoueurSseEmitters.get(idJoueur);
    }

    public void supprimerJeuSseEmitters(Long idJeu) {
        mapJeuSseEmitters.get().remove(idJeu.toString());
    }

    public void supprimerJoueurSseEmitter(String idJoueur, Map<String, SseEmitter> mapJoueurSseEmitters) {
        mapJoueurSseEmitters.remove(idJoueur);
    }

    public void notifier(Long idJeu) {
        Map<String, SseEmitter> mapJoueurSseEmitters = getMapJoueurSseEmitters(idJeu);
        if (mapJoueurSseEmitters != null) {
            try {
                for (Map.Entry<String, SseEmitter> joueurSseEmitter : mapJoueurSseEmitters.entrySet()) {
                    joueurSseEmitter.getValue().send(SseEmitter.event().name("message").data("recharger"));
                }
            } catch (IOException e) {
                log.error("Jeu "+idJeu+": Erreur d'envoi d'evenement");
            }
        }
    }

}