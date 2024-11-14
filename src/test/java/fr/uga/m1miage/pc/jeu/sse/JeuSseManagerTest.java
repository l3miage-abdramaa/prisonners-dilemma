package fr.uga.m1miage.pc.jeu.sse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JeuSseManagerTest {

    private JeuSseManager jeuSseManager;

    @BeforeEach
    void setUp() {
        jeuSseManager = new JeuSseManager();
    }

    @Test
    void testCreerNouveauSse() throws IOException {
        Long idJeu = 1L;
        String idJoueur = "joueur1";

        SseEmitter sseEmitter = jeuSseManager.creerNouveauSse(idJeu, idJoueur);

        assertNotNull(sseEmitter);
        assertNotNull(jeuSseManager.getMapJoueurSseEmitters(idJeu));
        assertEquals(1, jeuSseManager.getMapJoueurSseEmitters(idJeu).size());
        assertTrue(jeuSseManager.getMapJoueurSseEmitters(idJeu).containsKey(idJoueur));
    }

    @Test
    void testCreerNouveauSse_ExistingEmitter() throws IOException {
        Long idJeu = 1L;
        String idJoueur = "joueur1";

        // Créer le premier SseEmitter
        jeuSseManager.creerNouveauSse(idJeu, idJoueur);

        // Créer un nouveau SseEmitter pour le même joueur
        SseEmitter newEmitter = jeuSseManager.creerNouveauSse(idJeu, idJoueur);

        assertNotNull(newEmitter);
        assertEquals(1, jeuSseManager.getMapJoueurSseEmitters(idJeu).size());
    }

    @Test
    void testNotifier() throws IOException {
        Long idJeu = 1L;
        String idJoueur = "joueur1";
        // Créer un SseEmitter

        SseEmitter sseEmitter = jeuSseManager.creerNouveauSse(idJeu, idJoueur);
        // Mock de l'envoi de l'événement
        SseEmitter spyEmitter = Mockito.spy(sseEmitter);
        jeuSseManager.getMapJoueurSseEmitters(idJeu).put(idJoueur, spyEmitter);

        // Notifier les joueurs

        jeuSseManager.notifier(idJeu);
        // Utiliser ArgumentCaptor pour capturer l'argument passé à send
        ArgumentCaptor<SseEmitter.SseEventBuilder> captor = ArgumentCaptor.forClass(SseEmitter.SseEventBuilder.class);
        verify(spyEmitter).send(captor.capture());

    }

    @Test
    void testNotifier_NoEmitters() {
        Long idJeu = 1L;

        // Notifier sans émetteurs
        jeuSseManager.notifier(idJeu);
        // Pas d'exception, juste vérifier que ça ne plante
        assertDoesNotThrow(() -> jeuSseManager.notifier(idJeu));
    }

    @Test
    void testSupprimerJeuSseEmitters() throws IOException {
        Long idJeu = 1L;
        String idJoueur = "joueur1";

        // Créer un SseEmitter
        jeuSseManager.creerNouveauSse(idJeu, idJoueur);

        // Supprimer les émetteurs pour le jeu
        jeuSseManager.supprimerJeuSseEmitters(idJeu);

        // Vérifier que la map est vide
        assertNull(jeuSseManager.getMapJoueurSseEmitters(idJeu));
    }

    @Test
    void testSupprimerJoueurSseEmitter() throws IOException {
        Long idJeu = 1L;
        String idJoueur = "joueur1";

        // Cr éer un SseEmitter
        jeuSseManager.creerNouveauSse(idJeu, idJoueur);

        // Supprimer l'émetteur du joueur
        jeuSseManager.supprimerJoueurSseEmitter(idJoueur, jeuSseManager.getMapJoueurSseEmitters(idJeu));

        // Vérifier que l'émetteur du joueur a été supprimé
        assertNull(jeuSseManager.getJoueurSseEmitter(idJoueur, jeuSseManager.getMapJoueurSseEmitters(idJeu)));
    }
}