package fr.uga.m1miage.pc.serv.sse;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

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

        Assertions.assertNotNull(sseEmitter);
        Assertions.assertNotNull(jeuSseManager.getMapJoueurSseEmitters(idJeu));
        Assertions.assertEquals(1, jeuSseManager.getMapJoueurSseEmitters(idJeu).size());
        Assertions.assertTrue(jeuSseManager.getMapJoueurSseEmitters(idJeu).containsKey(idJoueur));
    }

    @Test
    void testCreerNouveauSse_ExistingEmitter() throws IOException {
        Long idJeu = 1L;
        String idJoueur = "joueur1";


        jeuSseManager.creerNouveauSse(idJeu, idJoueur);


        SseEmitter newEmitter = jeuSseManager.creerNouveauSse(idJeu, idJoueur);

        Assertions.assertNotNull(newEmitter);
        Assertions.assertEquals(1, jeuSseManager.getMapJoueurSseEmitters(idJeu).size());
    }

    @Test
    void testNotifier() throws IOException {
        Long idJeu = 1L;
        String idJoueur = "joueur1";


        SseEmitter sseEmitter = jeuSseManager.creerNouveauSse(idJeu, idJoueur);

        SseEmitter spyEmitter = Mockito.spy(sseEmitter);
        jeuSseManager.getMapJoueurSseEmitters(idJeu).put(idJoueur, spyEmitter);



        jeuSseManager.notifier(idJeu);

        ArgumentCaptor<SseEmitter.SseEventBuilder> captor = ArgumentCaptor.forClass(SseEmitter.SseEventBuilder.class);
        Mockito.verify(spyEmitter).send(captor.capture());

    }

    @Test
    void testNotifier_NoEmitters() {
        Long idJeu = 1L;
        jeuSseManager.notifier(idJeu);
        Assertions.assertDoesNotThrow(() -> jeuSseManager.notifier(idJeu));
    }

    @Test
    void testSupprimerJeuSseEmitters() throws IOException {
        Long idJeu = 1L;
        String idJoueur = "joueur1";

        jeuSseManager.creerNouveauSse(idJeu, idJoueur);


        jeuSseManager.supprimerJeuSseEmitters(idJeu);


        Assertions.assertNull(jeuSseManager.getMapJoueurSseEmitters(idJeu));
    }

    @Test
    void testSupprimerJoueurSseEmitter() throws IOException {
        Long idJeu = 1L;
        String idJoueur = "joueur1";


        jeuSseManager.creerNouveauSse(idJeu, idJoueur);


        jeuSseManager.supprimerJoueurSseEmitter(idJoueur, jeuSseManager.getMapJoueurSseEmitters(idJeu));

        Assertions.assertNull(jeuSseManager.getJoueurSseEmitter(idJoueur, jeuSseManager.getMapJoueurSseEmitters(idJeu)));
    }
}