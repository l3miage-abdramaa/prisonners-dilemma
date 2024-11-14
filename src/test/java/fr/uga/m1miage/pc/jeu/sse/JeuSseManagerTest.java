package fr.uga.m1miage.pc.jeu.sse;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class JeuSseManagerTest {

    @Mock
    private Map<String, Map<String, SseEmitter>> mapJeuSseEmittersMock;

    @InjectMocks
    private JeuSseManager jeuSseManager;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        jeuSseManager = new JeuSseManager(new ConcurrentHashMap<>());
    }

    @Test
    void testCreerNouveauSse() throws IOException {
        Long idJeu = 1L;
        String idJoueur = "joueur1";
        SseEmitter sseEmitter = jeuSseManager.creerNouveauSse(idJeu, idJoueur);

        assertNotNull(sseEmitter);
    }

    @Test
    void testNotifier() throws IOException {
        Long idJeu = 1L;
        Map<String, SseEmitter> mapJoueurSseEmitters = new ConcurrentHashMap<>();
        SseEmitter sseEmitter = new SseEmitter();
        mapJoueurSseEmitters.put("joueur1", sseEmitter);

        when(mapJeuSseEmittersMock.get(idJeu.toString())).thenReturn(mapJoueurSseEmitters);

        jeuSseManager.notifier(idJeu);

        verify(mapJeuSseEmittersMock).get(idJeu.toString());
    }
}

